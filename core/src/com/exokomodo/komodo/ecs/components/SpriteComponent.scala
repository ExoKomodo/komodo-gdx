package com.exokomodo.komodo.ecs.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.exokomodo.komodo.ecs.entities.Entity
import com.badlogic.gdx.graphics.g2d.Sprite
import java.rmi.UnexpectedException
import java.io.FileNotFoundException

object SpriteComponent extends BaseComponent {
  final val typeId = "SpriteComponent"

  def apply(parent: Entity, texturePath: String): SpriteComponent = {
    new SpriteComponent(
      Some(parent),
      Some(texturePath),
      None,
      None
    )
  }

  def apply(parent: Entity, texture: Texture): SpriteComponent = {
    new SpriteComponent(
      Some(parent),
      None,
      Some(texture),
      None,
    )
  }
}

@TypeId(id = SpriteComponent.typeId)
sealed class SpriteComponent(
                       override val parent: Option[Entity],
                       val texturePath: Option[String],
                       var texture: Option[Texture],
                       var sprite: Option[Sprite] = None,
                     ) extends BaseComponent {
  
  @throws(classOf[FileNotFoundException])
  override def initialize(): Unit = {
    if (isInitialized) return

    texture = texturePath match {
      case Some(path) => Some(
        new Texture(Gdx.files.internal(path))
      )
      case None => texture
    }
    sprite = texture match {
      case Some(t) => Some(new Sprite(t))
      case None => throw new FileNotFoundException("Texture could not be loaded")
    }
    super.initialize()
  }
}
