package com.exokomodo.komodo.test

import com.exokomodo.komodo.ecs
import com.exokomodo.komodo.ecs.BaseSystem

class TestSystem extends BaseSystem {
  override protected var registeredTypes: Set[String] = Set(
    ecs.getComponentTypeId(classOf[TestComponent]),
  )
}

object TestSystem {
  def test(): Unit = {
    var t = new TestSystem
    t.addComponent(new TestComponent)
  }
}