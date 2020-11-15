package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentTypeId, getComponentTypeId}

abstract class BaseSystem {
  protected var registeredTypes: Set[ComponentTypeId]

  def registerComponent(component: BaseComponent): Boolean = {
    if (!registeredTypes.contains(getComponentTypeId(component.getClass)))
      false
    else {
      // TODO: Add component
      true
    }
  }
}
