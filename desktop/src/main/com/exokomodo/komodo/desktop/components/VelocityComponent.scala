package com.exokomodo.komodo.desktop.components

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, TransformComponent, TypeId}
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.components.BaseComponent
import com.badlogic.gdx.math.Vector3

object VelocityComponent {
  final val typeId = "VelocityComponent"

  def apply(parent: Entity, velocity: Vector3, isEnabled: Boolean = true): VelocityComponent = {
    new VelocityComponent(
      Some(parent),
      velocity,
      isEnabled=isEnabled,
    )
  }
}

@TypeId(id = VelocityComponent.typeId)
class VelocityComponent(
                      override val parent: Option[Entity],
                      val velocity: Vector3,
                      override var isEnabled: Boolean = true,
                    ) extends BaseComponent {
  override def initialize(): Unit = {
    super.initialize()
  }
}
