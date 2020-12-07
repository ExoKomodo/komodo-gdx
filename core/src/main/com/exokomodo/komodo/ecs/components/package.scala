package com.exokomodo.komodo.ecs

package object components {
  type ComponentId = Int
  type ComponentTypeId = String

  private var _nextId: ComponentId = 0

  def getComponentTypeId[A <: BaseComponent](c: Class[A]): ComponentTypeId = {
    c.getAnnotation(classOf[TypeId]).id()
  }

  def nextId(): ComponentId = {
    _nextId += 1
    _nextId
  }
}

