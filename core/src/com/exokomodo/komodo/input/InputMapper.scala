package com.exokomodo.komodo.input

import com.badlogic.gdx.Input
import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.Controllers
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.Input.Buttons

object InputMapper {
  def fromGdxController(buttonCode: Int): Inputs = {
    Inputs.Undefined
  }

  def fromGdxKey(keyCode: Int): Inputs = {
    keyCode match {
      case Keys.A => Inputs.KeyA
      case Keys.B => Inputs.KeyB
      case Keys.C => Inputs.KeyC
      case Keys.D => Inputs.KeyD
      case Keys.E => Inputs.KeyE
      case Keys.F => Inputs.KeyF
      case Keys.G => Inputs.KeyG
      case Keys.H => Inputs.KeyH
      case Keys.I => Inputs.KeyI
      case Keys.J => Inputs.KeyJ
      case Keys.K => Inputs.KeyK
      case Keys.L => Inputs.KeyL
      case Keys.M => Inputs.KeyM
      case Keys.N => Inputs.KeyN
      case Keys.O => Inputs.KeyO
      case Keys.P => Inputs.KeyP
      case Keys.Q => Inputs.KeyQ
      case Keys.R => Inputs.KeyR
      case Keys.S => Inputs.KeyS
      case Keys.T => Inputs.KeyT
      case Keys.U => Inputs.KeyU
      case Keys.V => Inputs.KeyV
      case Keys.W => Inputs.KeyW
      case Keys.X => Inputs.KeyX
      case Keys.Y => Inputs.KeyY
      case Keys.Z => Inputs.KeyZ
      case Keys.NUM_0 => Inputs.Key0
      case Keys.NUM_1 => Inputs.Key1
      case Keys.NUM_2 => Inputs.Key2
      case Keys.NUM_3 => Inputs.Key3
      case Keys.NUM_4 => Inputs.Key4
      case Keys.NUM_5 => Inputs.Key5
      case Keys.NUM_6 => Inputs.Key6
      case Keys.NUM_7 => Inputs.Key7
      case Keys.NUM_8 => Inputs.Key8
      case Keys.NUM_9 => Inputs.Key9
      case Keys.BACKSPACE => Inputs.KeyBackSpace
      case Keys.BACKSLASH => Inputs.KeyBackslash
      case Keys.RIGHT_BRACKET => Inputs.KeyCloseBrackets
      case Keys.COMMA => Inputs.KeyComma
      case Keys.DOWN => Inputs.KeyDown
      case Keys.ENTER => Inputs.KeyEnter
      case Keys.ESCAPE => Inputs.KeyEscape
      case Keys.F1 => Inputs.KeyF1
      case Keys.F10 => Inputs.KeyF10
      case Keys.F11 => Inputs.KeyF11
      case Keys.F12 => Inputs.KeyF12
      case Keys.F2 => Inputs.KeyF2
      case Keys.F3 => Inputs.KeyF3
      case Keys.F4 => Inputs.KeyF4
      case Keys.F5 => Inputs.KeyF5
      case Keys.F6 => Inputs.KeyF6
      case Keys.F7 => Inputs.KeyF7
      case Keys.F8 => Inputs.KeyF8
      case Keys.F9 => Inputs.KeyF9
      case Keys.LEFT => Inputs.KeyLeft
      case Keys.ALT_LEFT => Inputs.KeyLeftAlt
      case Keys.CONTROL_LEFT => Inputs.KeyLeftControl
      case Keys.SHIFT_LEFT => Inputs.KeyLeftShift
      case Keys.MINUS => Inputs.KeyMinus
      case Keys.NUMPAD_0 => Inputs.KeyNumPad0
      case Keys.NUMPAD_1 => Inputs.KeyNumPad1
      case Keys.NUMPAD_2 => Inputs.KeyNumPad2
      case Keys.NUMPAD_3 => Inputs.KeyNumPad3
      case Keys.NUMPAD_4 => Inputs.KeyNumPad4
      case Keys.NUMPAD_5 => Inputs.KeyNumPad5
      case Keys.NUMPAD_6 => Inputs.KeyNumPad6
      case Keys.NUMPAD_7 => Inputs.KeyNumPad7
      case Keys.NUMPAD_8 => Inputs.KeyNumPad8
      case Keys.NUMPAD_9 => Inputs.KeyNumPad9
      case Keys.LEFT_BRACKET => Inputs.KeyOpenBrackets
      case Keys.PERIOD => Inputs.KeyPeriod
      case Keys.PLUS => Inputs.KeyPlus
      case Keys.RIGHT => Inputs.KeyRight
      case Keys.ALT_RIGHT => Inputs.KeyRightAlt
      case Keys.CONTROL_RIGHT => Inputs.KeyRightControl
      case Keys.SHIFT_RIGHT => Inputs.KeyRightShift
      case Keys.SEMICOLON => Inputs.KeySemicolon
      case Keys.SPACE => Inputs.KeySpace
      case Keys.TAB => Inputs.KeyTab
      case Keys.GRAVE => Inputs.KeyTilde
      case Keys.UP => Inputs.KeyUp
      case _ => Inputs.Undefined
    }
  }

  def fromGdxMouseButton(mouseButton: Int): Inputs = {
    mouseButton match {
      case Buttons.LEFT => Inputs.MouseLeftClick
      case Buttons.RIGHT => Inputs.MouseRightClick
      case Buttons.MIDDLE => Inputs.MouseMiddleClick
      case _ => Inputs.Undefined
    }
  }

  def toGdxKey(keyCode: Inputs): Int = {
    keyCode match {
      case Inputs.KeyA => Keys.A
      case Inputs.KeyB => Keys.B
      case Inputs.KeyC => Keys.C
      case Inputs.KeyD => Keys.D
      case Inputs.KeyE => Keys.E
      case Inputs.KeyF => Keys.F
      case Inputs.KeyG => Keys.G
      case Inputs.KeyH => Keys.H
      case Inputs.KeyI => Keys.I
      case Inputs.KeyJ => Keys.J
      case Inputs.KeyK => Keys.K
      case Inputs.KeyL => Keys.L
      case Inputs.KeyM => Keys.M
      case Inputs.KeyN => Keys.N
      case Inputs.KeyO => Keys.O
      case Inputs.KeyP => Keys.P
      case Inputs.KeyQ => Keys.Q
      case Inputs.KeyR => Keys.R
      case Inputs.KeyS => Keys.S
      case Inputs.KeyT => Keys.T
      case Inputs.KeyU => Keys.U
      case Inputs.KeyV => Keys.V
      case Inputs.KeyW => Keys.W
      case Inputs.KeyX => Keys.X
      case Inputs.KeyY => Keys.Y
      case Inputs.KeyZ => Keys.Z
      case Inputs.Key0 => Keys.NUM_0
      case Inputs.Key1 => Keys.NUM_1
      case Inputs.Key2 => Keys.NUM_2
      case Inputs.Key3 => Keys.NUM_3
      case Inputs.Key4 => Keys.NUM_4
      case Inputs.Key5 => Keys.NUM_5
      case Inputs.Key6 => Keys.NUM_6
      case Inputs.Key7 => Keys.NUM_7
      case Inputs.Key8 => Keys.NUM_8
      case Inputs.Key9 => Keys.NUM_9
      case Inputs.KeyBackSpace => Keys.BACKSPACE
      case Inputs.KeyBackslash => Keys.BACKSLASH
      case Inputs.KeyCloseBrackets => Keys.RIGHT_BRACKET
      case Inputs.KeyComma => Keys.COMMA
      case Inputs.KeyDown => Keys.DOWN
      case Inputs.KeyEnter => Keys.ENTER
      case Inputs.KeyEscape => Keys.ESCAPE
      case Inputs.KeyF1 => Keys.F1
      case Inputs.KeyF10 => Keys.F10
      case Inputs.KeyF11 => Keys.F11
      case Inputs.KeyF12 => Keys.F12
      case Inputs.KeyF2 => Keys.F2
      case Inputs.KeyF3 => Keys.F3
      case Inputs.KeyF4 => Keys.F4
      case Inputs.KeyF5 => Keys.F5
      case Inputs.KeyF6 => Keys.F6
      case Inputs.KeyF7 => Keys.F7
      case Inputs.KeyF8 => Keys.F8
      case Inputs.KeyF9 => Keys.F9
      case Inputs.KeyLeft => Keys.LEFT
      case Inputs.KeyLeftAlt => Keys.ALT_LEFT
      case Inputs.KeyLeftControl => Keys.CONTROL_LEFT
      case Inputs.KeyLeftShift => Keys.SHIFT_LEFT
      case Inputs.KeyMinus => Keys.MINUS
      case Inputs.KeyNumPad0 => Keys.NUMPAD_0
      case Inputs.KeyNumPad1 => Keys.NUMPAD_1
      case Inputs.KeyNumPad2 => Keys.NUMPAD_2
      case Inputs.KeyNumPad3 => Keys.NUMPAD_3
      case Inputs.KeyNumPad4 => Keys.NUMPAD_4
      case Inputs.KeyNumPad5 => Keys.NUMPAD_5
      case Inputs.KeyNumPad6 => Keys.NUMPAD_6
      case Inputs.KeyNumPad7 => Keys.NUMPAD_7
      case Inputs.KeyNumPad8 => Keys.NUMPAD_8
      case Inputs.KeyNumPad9 => Keys.NUMPAD_9
      case Inputs.KeyOpenBrackets => Keys.LEFT_BRACKET
      case Inputs.KeyPeriod => Keys.PERIOD
      case Inputs.KeyPlus => Keys.PLUS
      case Inputs.KeyRight => Keys.RIGHT
      case Inputs.KeyRightAlt => Keys.ALT_RIGHT
      case Inputs.KeyRightControl => Keys.CONTROL_RIGHT
      case Inputs.KeyRightShift => Keys.SHIFT_RIGHT
      case Inputs.KeySemicolon => Keys.SEMICOLON
      case Inputs.KeySpace => Keys.SPACE
      case Inputs.KeyTab => Keys.TAB
      case Inputs.KeyTilde => Keys.GRAVE
      case Inputs.KeyUp => Keys.UP
      case _ => Keys.UNKNOWN
    }
  }

  def toGdxMouseButton(mouseButton: Inputs): Int = {
    mouseButton match {
      case Inputs.MouseLeftClick => Buttons.LEFT
      case Inputs.MouseRightClick => Buttons.RIGHT
      case Inputs.MouseMiddleClick => Buttons.MIDDLE
      case _ => -1
    }
  }
}