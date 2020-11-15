package com.exokomodo.komodo.ecs.components

import com.exokomodo.komodo.ecs.entities.Entity

abstract class BaseComponent {
  val id: ComponentId = nextId()

  private var _isEnabled: Boolean = true
  def isEnabled = _isEnabled
  def isEnabled_=(v: Boolean): Unit = { _isEnabled = v }

  private var _parent: Option[Entity] = None
  def parent = _parent
  def parent_=(p: Entity): Unit = { _parent = Some(p) }
}
