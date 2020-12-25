package com.exokomodo.komodo.ecs.components

import com.badlogic.gdx.math.Vector3
import com.exokomodo.komodo.ecs.entities.Entity

object TransformComponent {
  final val typeId = "TransformComponent"

  def apply(
    parent: Entity,
    position: Vector3 = Vector3.Zero,
    rotation: Vector3 = Vector3.Zero,
    scale: Vector3 = new Vector3(1, 1, 1),
  ): TransformComponent = {
    new TransformComponent(
      Some(parent),
      position,
      rotation,
      scale,
    )
  }
}

@TypeId(id = TransformComponent.typeId)
sealed class TransformComponent(
  override val parent: Option[Entity],
  var position: Vector3,
  var rotation: Vector3,
  var scale: Vector3,
) extends BaseComponent {
  override def initialize(): Unit = {
    super.initialize()
  }
}
