package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{AsyncBehaviorComponent, getComponentTypeId}

class AsyncBehaviorSystem extends BaseSystem with AsyncUpdatableSystem {
  override protected final val _registeredTypes = Set(
    AsyncBehaviorComponent.typeId,
  )

  override def update(): Unit = {
    _entityToComponents.foreachEntry((_, components) => {
      components.foreach(component => {
        _updateComponent(component.asInstanceOf[AsyncBehaviorComponent])
      })
    })
  }

  def _updateComponent(component: AsyncBehaviorComponent): Unit =
    if (component.isReady) component.execute()
}
