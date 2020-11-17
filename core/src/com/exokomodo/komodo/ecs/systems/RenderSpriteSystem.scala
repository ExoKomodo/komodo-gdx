package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.Engine
import com.exokomodo.komodo.ecs.components.{
  ComponentTypeId,
  SpriteComponent,
  TransformComponent,
  getComponentTypeId,
}

object RenderSpriteSystem {
  def apply(): RenderSpriteSystem = {
    new RenderSpriteSystem
  }
}

class RenderSpriteSystem extends BaseSystem with DrawableSystem {
  override protected var _registeredTypes: Set[ComponentTypeId] = Set(
    getComponentTypeId(classOf[SpriteComponent]),
    getComponentTypeId(classOf[TransformComponent]),
  )

  override def draw(): Unit = {
    Engine.spriteBatch.get.begin()
    _entityToComponents.foreachEntry((_, components) => {
      val sprite = _findComponentByTypeId(components, classOf[SpriteComponent])
      val transform = _findComponentByTypeId(components, classOf[TransformComponent])
      (sprite, transform) match {
        case (Some(s), Some(t)) => _drawComponents(s, t)
        case _ => ()
      }
    })
    Engine.spriteBatch.get.end()
  }

  private def _drawComponents(sprite: SpriteComponent, transform: TransformComponent): Unit = {
    Engine.spriteBatch.get.draw(
      sprite.texture.get,
      transform.position.x,
      transform.position.y,
    )
  }
}
