package com.exokomodo.komodo.ecs.entities

import com.exokomodo.komodo.Game
import com.exokomodo.komodo.ecs.{components, systems}
import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentTypeId, getComponentTypeId}
import com.exokomodo.komodo.ecs.systems.SystemId

object Entity {
  def apply(): Entity = {
    new Entity
  }
}

sealed class Entity {
  val id: EntityId = nextId()

  def findComponentByClass[A <: BaseComponent](c: Class[A]): Option[A] = {
    findComponentByTypeId[A](components.getComponentTypeId(c))
  }

  def findComponentByTypeId[A <: BaseComponent](typeId: ComponentTypeId): Option[A] = {
    var result: Option[A] = None
    Game.executeOnSystems((_: SystemId, system: systems.System) => {
      result match {
        case None => system.findComponentByParent[A](this, typeId) match {
          case Some(component) => {
            result = Some(component)
          }
          case None => ()
        }
        case Some(_) => ()
      }
    })
    result
  }
}
