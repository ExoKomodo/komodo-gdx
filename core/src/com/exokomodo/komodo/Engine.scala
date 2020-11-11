package com.exokomodo.komodo

import com.exokomodo.komodo.ecs.{Entity, BaseSystem}

trait Engine {
  def registerSystem[A <: BaseSystem](system: A): Boolean
}
