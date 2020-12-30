package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, getComponentTypeId}
import com.exokomodo.komodo.desktop.components.{RotationComponent, VelocityComponent}
import com.exokomodo.komodo.ecs.components.SpriteComponent
import com.exokomodo.komodo.ecs.components.TransformComponent
import com.exokomodo.komodo.lib.Vector3


class MoveAndRotateSpriteSystem extends BaseSystem with UpdatableSystem {
  override protected final val _registeredTypes = Set(
    SpriteComponent.typeId,
    TransformComponent.typeId,
    VelocityComponent.typeId,
    RotationComponent.typeId,
  )

  override def update(delta: Float): Unit = {
    _entityToComponents.foreachEntry((_, components) => {
      val transform = _findComponentByClass(components, classOf[TransformComponent])
      val velocity = _findComponentByClass(components, classOf[VelocityComponent])
      val rotation = _findComponentByClass(components, classOf[RotationComponent])
      (transform, velocity, rotation) match {
        case (Some(t), Some(v), Some(r)) => _moveAndRotate(t, v, r, delta)
        case _ => ()
      }
    })
  }

  def _moveAndRotate(
    transform: TransformComponent,
    velocity: VelocityComponent,
    rotation: RotationComponent,
    delta: Float,
  ): Unit = {
    if (
      !transform.isReady
      || !velocity.isReady
      || !rotation.isReady
    )
      return
    else {
      transform.translate(
        velocity.velocity.scale(delta)
      )
      transform.rotate(
        Vector3.apply(0, 0, rotation.rotation * delta)
      )
    }
  }
}
