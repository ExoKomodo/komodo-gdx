package com.exokomodo.komodo.input

sealed trait InputState

object InputState {
    case object Undefined extends InputState
    case object Up extends InputState
    case object Down extends InputState
}