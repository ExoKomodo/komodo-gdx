package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, getComponentTypeId}


class BehaviorSystem extends BaseSystem with UpdatableSystem {
  override protected final val _registeredTypes = Set(
    BehaviorComponent.typeId,
  )

  override def update(delta: Float): Unit = {
    _entityToComponents.foreachEntry((_, components) => {
      components.foreach(component => {
        _updateComponent(component.asInstanceOf[BehaviorComponent], delta)
      })
    })
  }

  def _updateComponent(component: BehaviorComponent, delta: Float): Unit =
    if (!component.isReady)
      return
    else
      component.update(delta)
}
