package com.exokomodo.komodo.test

import com.exokomodo.komodo.ecs.components.BaseComponent

object TestComponent extends BaseComponent {
  def apply(): TestComponent = {
    new TestComponent
  }
}

class TestComponent extends BaseComponent {
}