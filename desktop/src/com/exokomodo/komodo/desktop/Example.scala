package com.exokomodo.komodo.desktop

import com.badlogic.gdx.math.Vector3
import com.exokomodo.komodo.Game
import com.exokomodo.komodo.desktop.behaviors.MoveBehavior
import com.exokomodo.komodo.ecs.components.{SpriteComponent, TransformComponent}
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.systems.{BehaviorSystem, RenderSpriteSystem}

object Example extends App {
  val entity = new Entity

  Game.registerSystem(new BehaviorSystem)
  Game.registerSystem(new RenderSpriteSystem)
  Game.registerEntity(entity)

  Game.registerComponent(
    TransformComponent.apply(
      entity,
      position = new Vector3(50, 50, 0),
    )
  )
  Game.registerComponent(
    SpriteComponent.apply(
      entity,
      "badlogic.jpg",
    )
  )
  Game.registerComponent(MoveBehavior.apply(entity))

  Game.run()
}
