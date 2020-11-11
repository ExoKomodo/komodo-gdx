package com.exokomodo.komodo

package object ecs {
  def createEntity(): Entity = {
    null
  }

  def createSystem[A <: BaseSystem](): BaseSystem = {
    null
  }

  def getComponentTypeId[A <: BaseComponent](c: Class[A]): String = {
    c.getTypeName
  }
}
