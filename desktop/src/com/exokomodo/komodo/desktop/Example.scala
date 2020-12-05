package com.exokomodo.komodo.desktop

import com.badlogic.gdx.math.Vector3
import com.exokomodo.komodo.Game
import com.exokomodo.komodo.desktop.behaviors.MoveBehavior
import com.exokomodo.komodo.ecs.components.{SpriteComponent, TransformComponent}
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.systems.{BehaviorSystem, RenderSpriteSystem}
import com.exokomodo.komodo.desktop.behaviors.RotateBehavior
import com.exokomodo.komodo.ecs.systems.MoveAndRotateSpriteSystem
import com.exokomodo.komodo.desktop.components.{RotationComponent, VelocityComponent}

object Example extends App {
  val entity = new Entity

  Game.registerSystem(new BehaviorSystem)
  Game.registerSystem(new RenderSpriteSystem)
  // Game.registerSystem(new MoveAndRotateSpriteSystem)
  Game.registerEntity(entity)

  Game.registerComponent(
    TransformComponent.apply(
      entity,
      position = new Vector3(50, 50, 0),
      rotation = new Vector3(0, 0, 0),
      scale = new Vector3(1, 1, 0),
    )
  )
  Game.registerComponent(
    SpriteComponent.apply(
      entity,
      "badlogic.jpg",
    )
  )
  // Game.registerComponent(
  //   VelocityComponent.apply(
  //     entity,
  //     new Vector3(100, 0, 0),
  //   )
  // )
  // Game.registerComponent(
  //   RotationComponent.apply(
  //     entity,
  //     new Vector3(0, 0, 90),
  //   )
  // )
  Game.registerComponent(MoveBehavior.apply(entity))
  // Game.registerComponent(RotateBehavior.apply(entity))

  Game.run()
}
