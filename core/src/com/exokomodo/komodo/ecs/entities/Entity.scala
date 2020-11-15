package com.exokomodo.komodo.ecs.entities

class Entity {
  val id: EntityId = nextId()
}

object Entity {
  def apply(): Entity = {
    new Entity
  }
}
