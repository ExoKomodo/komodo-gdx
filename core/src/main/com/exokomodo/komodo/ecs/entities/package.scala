package com.exokomodo.komodo.ecs

package object entities {
  type EntityId = Int

  private var _nextId: EntityId = 0

  def nextId(): EntityId = {
    _nextId += 1
    _nextId
  }
}
