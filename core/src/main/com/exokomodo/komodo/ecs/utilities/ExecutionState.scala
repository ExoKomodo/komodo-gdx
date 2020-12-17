package com.exokomodo.komodo.ecs.utilities

sealed trait ExecutionState

object ExecutionState {
    case object NotStarted extends ExecutionState
    case object Running extends ExecutionState
    case object Finished extends ExecutionState
}