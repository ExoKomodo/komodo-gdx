package com.exokomodo.komodo.desktop.behaviors

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, TransformComponent, TypeId}
import com.exokomodo.komodo.ecs.entities.Entity

object MoveBehavior {
  def apply(parent: Entity): MoveBehavior = {
    new MoveBehavior(Some(parent))
  }
}

@TypeId(id = BehaviorComponent.typeId)
class MoveBehavior(override val parent: Option[Entity]) extends BehaviorComponent(parent) {
  private var _transform: Option[TransformComponent] = None

  override def initialize(): Unit = {
    super.initialize()

    _transform = parent.get.findComponentByClass(classOf[TransformComponent])
  }

  override def update(delta: Float): Unit = {
    _transform.get.position.x += 1
  }
}
