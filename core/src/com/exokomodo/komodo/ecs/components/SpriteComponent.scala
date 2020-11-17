package com.exokomodo.komodo.ecs.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.exokomodo.komodo.ecs.entities.Entity

object SpriteComponent extends BaseComponent {
  def apply(parent: Entity, texturePath: String): SpriteComponent = {
    new SpriteComponent(
      Some(parent),
      Some(texturePath),
      None,
    )
  }

  def apply(parent: Entity, texture: Texture): SpriteComponent = {
    new SpriteComponent(
      Some(parent),
      None,
      Some(texture),
    )
  }
}

@TypeId(id = "SpriteComponent")
sealed class SpriteComponent(
                       override val parent: Option[Entity],
                       val texturePath: Option[String],
                       var texture: Option[Texture],
                     ) extends BaseComponent {
  override def initialize(): Unit = {
    if (isInitialized) return

    texturePath match {
      case Some(path) => texture = Some(
        new Texture(Gdx.files.internal(path))
      )
      case None => ()
    }
    super.initialize()
  }
}
