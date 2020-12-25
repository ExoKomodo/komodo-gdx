package com.exokomodo.komodo.ecs.components

import com.exokomodo.komodo.ecs.entities.Entity

object BehaviorComponent {
  final val typeId = "BehaviorComponent"
}

@TypeId(id = BehaviorComponent.typeId)
abstract class BehaviorComponent(
  override val parent: Option[Entity],
) extends BaseComponent {
  override def initialize(): Unit = {
    if (isInitialized) return

    super.initialize()
  }

  def update(delta: Float): Unit
}
