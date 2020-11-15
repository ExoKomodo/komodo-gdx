package com.exokomodo.komodo.test

import com.exokomodo.komodo.ecs.components.getComponentTypeId
import com.exokomodo.komodo.ecs.systems.{BaseSystem}

object TestSystem {
  def apply(): TestSystem = {
    new TestSystem
  }
}

class TestSystem extends BaseSystem {
  override protected var registeredTypes: Set[String] = Set(
    getComponentTypeId(classOf[TestComponent]),
  )
}