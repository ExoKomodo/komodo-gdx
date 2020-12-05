package com.exokomodo.komodo.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.{Buttons, Keys}
import com.badlogic.gdx.controllers.{Controller, Controllers}
import com.badlogic.gdx.math.Vector2
import scala.collection.immutable.{HashMap, Set}

object InputManager {
    type Action = String
    type InputInfoMap = HashMap[Inputs, InputInfo]
    type ActionMap = HashMap[Action, InputSet]
    type InputSet = Set[Inputs]
    type InputInfoSet = Set[Option[InputInfo]]

    private var _actionMap: ActionMap = HashMap.empty[Action, InputSet]
    private var _inputState: InputInfoMap = HashMap.empty[Inputs, InputInfo]
    private var _previousInputState: InputInfoMap = HashMap.empty[Inputs, InputInfo]
    private val _inputsToPoll: List[Inputs] = List(
        // Gamepads
        // Inputs.ButtonA,
        // Inputs.ButtonB,
        // Inputs.ButtonDown,
        // Inputs.ButtonHome,
        // Inputs.ButtonL1,
        // Inputs.ButtonL2,
        // Inputs.ButtonL3,
        // Inputs.ButtonLeft,
        // Inputs.ButtonR1,
        // Inputs.ButtonR2,
        // Inputs.ButtonR3,
        // Inputs.ButtonRight,
        // Inputs.ButtonSelect,
        // Inputs.ButtonStart,
        // Inputs.ButtonUp,
        // Inputs.ButtonX,
        // Inputs.ButtonY,
        // Inputs.ThumbstickLeft,
        // Inputs.ThumbstickRight,
        // Keyboard
        Inputs.Key0,
        Inputs.Key1,
        Inputs.Key2,
        Inputs.Key3,
        Inputs.Key4,
        Inputs.Key5,
        Inputs.Key6,
        Inputs.Key7,
        Inputs.Key8,
        Inputs.Key9,
        Inputs.KeyA,
        Inputs.KeyB,
        Inputs.KeyBackSpace,
        Inputs.KeyBackslash,
        Inputs.KeyC,
        Inputs.KeyCloseBrackets,
        Inputs.KeyComma,
        Inputs.KeyD,
        Inputs.KeyDown,
        Inputs.KeyE,
        Inputs.KeyEnter,
        Inputs.KeyEscape,
        Inputs.KeyF,
        Inputs.KeyF1,
        Inputs.KeyF10,
        Inputs.KeyF11,
        Inputs.KeyF12,
        Inputs.KeyF2,
        Inputs.KeyF3,
        Inputs.KeyF4,
        Inputs.KeyF5,
        Inputs.KeyF6,
        Inputs.KeyF7,
        Inputs.KeyF8,
        Inputs.KeyF9,
        Inputs.KeyG,
        Inputs.KeyH,
        Inputs.KeyI,
        Inputs.KeyJ,
        Inputs.KeyK,
        Inputs.KeyL,
        Inputs.KeyLeft,
        Inputs.KeyLeftAlt,
        Inputs.KeyLeftControl,
        Inputs.KeyLeftShift,
        Inputs.KeyM,
        Inputs.KeyMinus,
        Inputs.KeyN,
        Inputs.KeyNumPad0,
        Inputs.KeyNumPad1,
        Inputs.KeyNumPad2,
        Inputs.KeyNumPad3,
        Inputs.KeyNumPad4,
        Inputs.KeyNumPad5,
        Inputs.KeyNumPad6,
        Inputs.KeyNumPad7,
        Inputs.KeyNumPad8,
        Inputs.KeyNumPad9,
        Inputs.KeyO,
        Inputs.KeyOpenBrackets,
        Inputs.KeyP,
        Inputs.KeyPeriod,
        Inputs.KeyPlus,
        Inputs.KeyQ,
        Inputs.KeyR,
        Inputs.KeyRight,
        Inputs.KeyRightAlt,
        Inputs.KeyRightControl,
        Inputs.KeyRightShift,
        Inputs.KeyS,
        Inputs.KeySemicolon,
        Inputs.KeySpace,
        Inputs.KeyT,
        Inputs.KeyTab,
        Inputs.KeyTilde,
        Inputs.KeyU,
        Inputs.KeyUp,
        Inputs.KeyV,
        Inputs.KeyW,
        Inputs.KeyX,
        Inputs.KeyY,
        Inputs.KeyZ,
        // Mouse
        Inputs.MouseLeftClick,
        Inputs.MouseMiddleClick,
        Inputs.MouseRightClick,
    )

    def addInputMapping(action: Action, input: Inputs): Unit = {
        val inputSet = _actionMap.get(action) match {
            case Some(inputSet) => inputSet
            case None => Set.empty[Inputs]
        }
        if (inputSet.contains(input)) {
            return
        }
        _actionMap = _actionMap + (
            action -> (inputSet + input)
        )
    }

    def getAction(action: Action, isCurrent: Boolean = true): InputInfoSet = {
        _actionMap.get(action) match {
            case Some(inputSet) => inputSet.map(i => _getInputInfo(i, isCurrent))
            case None => Set.empty[Option[InputInfo]]
        }
    }

    def getMousePosition(isCurrent: Boolean): Vector2 = {
        val x = Gdx.input.getX().toFloat
        val y = Gdx.input.getY().toFloat
        if (isCurrent)
            new Vector2( x,y)
        else
            new Vector2(
                x - Gdx.input.getDeltaX(),
                y - Gdx.input.getDeltaY(),
            )
    }

    def isActionJustPressed(action: String): Boolean = {
        _gdxInputCheckCommon(
            action,
            Gdx.input.isKeyJustPressed,
            Gdx.input.isButtonJustPressed,
        )
    }
    
    def isActionJustReleased(action: String): Boolean = {
        _inputCheckCommon(action, (input: Inputs) => {
            (_previousInputState.get(input), _inputState.get(input)) match {
                case (Some(previous), Some(current)) => {
                    (previous.state, current.state) match {
                        case (InputState.Down, InputState.Up) => return true
                        case _ => ()
                    }
                }
                case _ => ()
            }
            false
        })
    }
    
    def isActionPressed(action: String): Boolean = {
        _gdxInputCheckCommon(
            action,
            Gdx.input.isKeyPressed,
            Gdx.input.isButtonPressed,
        )
    }

    def isActionReleased(action: String): Boolean = {
        _inputCheckCommon(action, (input: Inputs) => {
            (_inputState.get(input)) match {
                case Some(current) => {
                    current.state match {
                        case InputState.Up => return true
                        case _ => ()
                    }
                }
                case _ => ()
            }
            false
        })
    }

    def update(): Unit = {
        _previousInputState = _inputState
        _inputState = _pollInputState()
    }

    private def _constructInputInfo(input: Inputs): InputInfo = {
        // TODO: Get input state, direction, and strength
        val state = _getInputState(input)
        InputInfo(
            input = input,
            state = state,
            direction = _getInputDirection(input, state),
            strength = _getInputStrength(input, state),
        )
    }

    def _gdxInputCheckCommon(action: String, keyFn: (Int) => Boolean, mouseFn: (Int) => Boolean): Boolean = {
        for (input <- InputManager.getAction(action)) {
            input match {
                case Some(value) => {
                    value.input match {
                        case Inputs.MouseLeftClick | Inputs.MouseRightClick | Inputs.MouseMiddleClick => {
                            val gdxInput = InputMapper.toGdxMouseButton(value.input)
                            if (mouseFn(gdxInput))
                                return true
                        }
                        case _ => {
                            val gdxInput = InputMapper.toGdxKey(value.input)
                            if (keyFn(gdxInput))
                                return true
                        }
                    }
                }
                case None => ()
            }
        }
        false
    }

    private def _getInputDirection(input: Inputs, state: InputState): Vector2 = {
        Vector2.Zero
    }

    private def _getInputInfo(input: Inputs, isCurrent: Boolean = true): Option[InputInfo] = {
        if (isCurrent)
            _inputState.get(input)
        else
            _previousInputState.get(input)
    }

    private def _getInputState(input: Inputs): InputState = {
        input match {
            case Inputs.MouseLeftClick | Inputs.MouseRightClick | Inputs.MouseMiddleClick => {
                val gdxInput = InputMapper.toGdxMouseButton(input)
                if (Gdx.input.isButtonPressed(gdxInput))
                    InputState.Down
                else
                    InputState.Up
            }
            case _ => {
                val gdxInput = InputMapper.toGdxKey(input)
                if (Gdx.input.isKeyPressed(gdxInput))
                    InputState.Down
                else
                    InputState.Up
            }
        }
    }

    private def _getInputStrength(input: Inputs, state: InputState): Float = {
        state match {
            case InputState.Down => 1
            case _ => 0
        }
    }

    def _inputCheckCommon(action: String, fn: (Inputs) => Boolean): Boolean = {
        for (input <- InputManager.getAction(action)) {
            input match {
                case Some(value) => {
                    if (fn(value.input))
                        return true
                }
                case None => ()
            }
        }
        false
    }

    private def _pollInputState(): InputInfoMap = {
        HashMap.from(
            _inputsToPoll.map(input => (input, _constructInputInfo(input)))
        )
    }
}