package com.exokomodo.komodo.input

import com.badlogic.gdx.math.Vector2

case class InputInfo(
  val input: Inputs = Inputs.Undefined,
  val state: InputState = InputState.Undefined,
  val direction: Vector2 = Vector2.Zero,
  val strength: Float,
)