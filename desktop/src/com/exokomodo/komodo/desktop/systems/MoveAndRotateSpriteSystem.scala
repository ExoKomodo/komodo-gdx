package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, getComponentTypeId}
import com.exokomodo.komodo.desktop.components.{RotationComponent, VelocityComponent}
import com.exokomodo.komodo.ecs.components.SpriteComponent
import com.exokomodo.komodo.ecs.components.TransformComponent


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

  def _moveAndRotate(t: TransformComponent, v: VelocityComponent, r: RotationComponent, delta: Float): Unit = {
    if (
      !t.isReady
      || !v.isReady
      || !r.isReady
    )
      return
    else {
      val movement = v.velocity.cpy().scl(delta)
      t.position.add(movement)
      
      val rotation = r.rotation.cpy().scl(delta)
      t.rotation.add(rotation)
    }
  }
}
