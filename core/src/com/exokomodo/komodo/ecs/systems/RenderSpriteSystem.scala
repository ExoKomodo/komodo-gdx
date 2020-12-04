package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.Engine
import com.exokomodo.komodo.ecs.components.{
  ComponentTypeId,
  SpriteComponent,
  TransformComponent,
  getComponentTypeId,
}
import com.badlogic.gdx.graphics.g2d.Sprite

object RenderSpriteSystem {
  def apply(): RenderSpriteSystem = {
    new RenderSpriteSystem
  }
}

class RenderSpriteSystem extends BaseSystem with DrawableSystem {
  override protected final val _registeredTypes = Set(
    SpriteComponent.typeId,
    TransformComponent.typeId,
  )

  override def draw(): Unit = {
    Engine.spriteBatch.get.begin()
    _entityToComponents.foreachEntry((_, components) => {
      val sprite = _findComponentByClass(components, classOf[SpriteComponent])
      val transform = _findComponentByClass(components, classOf[TransformComponent])
      (sprite, transform) match {
        case (Some(s), Some(t)) => _drawComponents(s, t)
        case _ => ()
      }
    })
    Engine.spriteBatch.get.end()
  }

  private def _drawComponents(sprite: SpriteComponent, transform: TransformComponent): Unit = {
    if (!sprite.isReady || !transform.isReady)
      return
    else {
      val internalSprite = sprite.sprite.get
      _prepareSpriteToDraw(internalSprite, transform)
      internalSprite.draw(Engine.spriteBatch.get)
    }
  }

  private def _prepareSpriteToDraw(sprite: Sprite, transform: TransformComponent): Unit = {
    val scale = transform.scale
    sprite.setScale(scale.x, scale.y)
    
    // Negative so rotation is clockwise
    val rotation = transform.rotation
    sprite.setRotation(-transform.rotation.z)
    
    val position = transform.position
    sprite.setPosition(position.x, position.y)
  }
}
