package com.exokomodo.komodo.ecs

import com.exokomodo.komodo.ecs.components.BaseComponent

package object systems {
  trait System {
    def initialize(): Unit
    def registerComponent(component: BaseComponent): Boolean
  }

  trait DrawableSystem extends System {
    def draw(): Unit
  }

  trait UpdatableSystem extends System {
    def update(delta: Float): Unit
  }
}
