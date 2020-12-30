package com.exokomodo.komodo.editor.views

import com.exokomodo.komodo.Game
import com.exokomodo.komodo.ecs.components.SpriteComponent
import com.exokomodo.komodo.ecs.components.TransformComponent
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.systems.RenderSpriteSystem
import com.exokomodo.komodo.editor.controllers.MainController
import com.exokomodo.komodo.editor.views.KomodoView
import com.exokomodo.komodo.lib.Vector3


class GameView extends KomodoView {
  def setupDemo(): Unit = {
    Game.reset()
    val entity = new Entity

    Game.registerSystem(new RenderSpriteSystem)
    Game.registerEntity(entity)

    Game.registerComponent(
      TransformComponent.apply(
        entity,
        position = Vector3.apply(50, 50, 0),
        rotation = Vector3.apply(0, 0, 0),
        scale = Vector3.apply(1, 1, 0),
      )
    )
    Game.registerComponent(
      SpriteComponent.apply(
        entity,
        "./assets/img/badlogic.jpg",
      )
    )
  }

  def show(): Unit = {
    Game.runInEditor()
  }

  override def stop: Unit = ()
}
