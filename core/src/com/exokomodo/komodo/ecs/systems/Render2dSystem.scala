package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{ComponentTypeId, SpriteComponent, TransformComponent, getComponentTypeId}

object Render2dSystem {
  def apply(): Render2dSystem = {
    new Render2dSystem
  }
}

class Render2dSystem extends BaseSystem with DrawableSystem {
  override protected var registeredTypes: Set[ComponentTypeId] = Set(
    getComponentTypeId(classOf[TransformComponent]),
    getComponentTypeId(classOf[SpriteComponent]),
  )

  override def draw(): Unit = {

  }
}
