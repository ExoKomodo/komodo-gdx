package com.exokomodo.komodo.desktop.components

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, TransformComponent, TypeId}
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.components.BaseComponent
import com.badlogic.gdx.math.Vector3

object RotationComponent {
  final val typeId = "RotationComponent"

  def apply(parent: Entity, rotation: Vector3, isEnabled: Boolean = true): RotationComponent = {
    new RotationComponent(
      Some(parent),
      rotation,
      isEnabled=isEnabled,
    )
  }
}

@TypeId(id = RotationComponent.typeId)
class RotationComponent(
                      override val parent: Option[Entity],
                      val rotation: Vector3,
                      override var isEnabled: Boolean = true,
                    ) extends BaseComponent {
  override def initialize(): Unit = {
    super.initialize()
  }
}
