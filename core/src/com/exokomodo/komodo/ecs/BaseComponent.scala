package com.exokomodo.komodo.ecs

abstract class BaseComponent {
  val id: Int = BaseComponent.nextId
}

object BaseComponent {
  private var _nextId: Int = 0

  def nextId = {
    _nextId += 1
    _nextId
  }
}