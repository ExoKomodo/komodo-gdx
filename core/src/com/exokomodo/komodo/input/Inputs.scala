package com.exokomodo.komodo.input

sealed trait Inputs

object Inputs {
    case object Undefined extends Inputs
    // Gamepad
    // case object ButtonA extends Inputs
    // case object ButtonB extends Inputs
    // case object ButtonDown extends Inputs
    // case object ButtonHome extends Inputs
    // case object ButtonL1 extends Inputs
    // case object ButtonL2 extends Inputs
    // case object ButtonL3 extends Inputs
    // case object ButtonLeft extends Inputs
    // case object ButtonR1 extends Inputs
    // case object ButtonR2 extends Inputs
    // case object ButtonR3 extends Inputs
    // case object ButtonRight extends Inputs
    // case object ButtonSelect extends Inputs
    // case object ButtonStart extends Inputs
    // case object ButtonUp extends Inputs
    // case object ButtonX extends Inputs
    // case object ButtonY extends Inputs
    // case object ThumbstickLeft extends Inputs
    // case object ThumbstickRight extends Inputs
    // Keyboard
    case object Key0 extends Inputs
    case object Key1 extends Inputs
    case object Key2 extends Inputs
    case object Key3 extends Inputs
    case object Key4 extends Inputs
    case object Key5 extends Inputs
    case object Key6 extends Inputs
    case object Key7 extends Inputs
    case object Key8 extends Inputs
    case object Key9 extends Inputs
    case object KeyA extends Inputs
    case object KeyB extends Inputs
    case object KeyBackSpace extends Inputs
    case object KeyBackslash extends Inputs
    case object KeyC extends Inputs
    case object KeyCloseBrackets extends Inputs
    case object KeyComma extends Inputs
    case object KeyD extends Inputs
    case object KeyDown extends Inputs
    case object KeyE extends Inputs
    case object KeyEnter extends Inputs
    case object KeyEscape extends Inputs
    case object KeyF extends Inputs
    case object KeyF1 extends Inputs
    case object KeyF10 extends Inputs
    case object KeyF11 extends Inputs
    case object KeyF12 extends Inputs
    case object KeyF2 extends Inputs
    case object KeyF3 extends Inputs
    case object KeyF4 extends Inputs
    case object KeyF5 extends Inputs
    case object KeyF6 extends Inputs
    case object KeyF7 extends Inputs
    case object KeyF8 extends Inputs
    case object KeyF9 extends Inputs
    case object KeyG extends Inputs
    case object KeyH extends Inputs
    case object KeyI extends Inputs
    case object KeyJ extends Inputs
    case object KeyK extends Inputs
    case object KeyL extends Inputs
    case object KeyLeft extends Inputs
    case object KeyLeftAlt extends Inputs
    case object KeyLeftControl extends Inputs
    case object KeyLeftShift extends Inputs
    case object KeyM extends Inputs
    case object KeyMinus extends Inputs
    case object KeyN extends Inputs
    case object KeyNumPad0 extends Inputs
    case object KeyNumPad1 extends Inputs
    case object KeyNumPad2 extends Inputs
    case object KeyNumPad3 extends Inputs
    case object KeyNumPad4 extends Inputs
    case object KeyNumPad5 extends Inputs
    case object KeyNumPad6 extends Inputs
    case object KeyNumPad7 extends Inputs
    case object KeyNumPad8 extends Inputs
    case object KeyNumPad9 extends Inputs
    case object KeyO extends Inputs
    case object KeyOpenBrackets extends Inputs
    case object KeyP extends Inputs
    case object KeyPeriod extends Inputs
    case object KeyPlus extends Inputs
    case object KeyQ extends Inputs
    case object KeyR extends Inputs
    case object KeyRight extends Inputs
    case object KeyRightAlt extends Inputs
    case object KeyRightControl extends Inputs
    case object KeyRightShift extends Inputs
    case object KeyS extends Inputs
    case object KeySemicolon extends Inputs
    case object KeySpace extends Inputs
    case object KeyT extends Inputs
    case object KeyTab extends Inputs
    case object KeyTilde extends Inputs
    case object KeyU extends Inputs
    case object KeyUp extends Inputs
    case object KeyV extends Inputs
    case object KeyW extends Inputs
    case object KeyX extends Inputs
    case object KeyY extends Inputs
    case object KeyZ extends Inputs
    // Mouse
    case object MouseLeftClick extends Inputs
    case object MouseMiddleClick extends Inputs
    case object MouseRightClick extends Inputs
}