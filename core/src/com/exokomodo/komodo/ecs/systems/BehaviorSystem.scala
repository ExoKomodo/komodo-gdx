package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, getComponentTypeId}


class BehaviorSystem extends BaseSystem with UpdatableSystem {
  override protected final val _registeredTypes = Set(
    getComponentTypeId(classOf[BehaviorComponent]),
  )

  override def update(delta: Float): Unit = {
    _entityToComponents.foreachEntry((_, components) => {
      val behavior = _findComponentByClass(components, classOf[BehaviorComponent])
      behavior match {
        case Some(b) => _updateComponent(b, delta)
        case _ => ()
      }
    })
  }

  def _updateComponent(component: BehaviorComponent, delta: Float): Unit =
    component.update(delta)
}
