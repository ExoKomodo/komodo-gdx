package com.exokomodo.komodo.ecs

package object systems {
  trait DrawableSystem {
    def draw(): Unit
  }

  trait UpdatableSystem {
    def update(delta: Float): Unit
  }
}
