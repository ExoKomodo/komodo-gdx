package com.exokomodo.komodo.ecs

trait BaseSystem {
  protected var registeredTypes: Set[String]

  def addComponent(component: BaseComponent): Boolean = {
    if (!registeredTypes.contains(getComponentTypeId(component.getClass)))
      false
    else {
      // TODO: Add component
      true
    }
  }
}
