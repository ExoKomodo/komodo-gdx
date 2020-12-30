package com.exokomodo.komodo.desktop

import com.exokomodo.komodo.Game
import com.exokomodo.komodo.desktop.behaviors.MoveBehavior
import com.exokomodo.komodo.ecs.components.{SpriteComponent, TransformComponent}
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.systems.{BehaviorSystem, RenderSpriteSystem}
import com.exokomodo.komodo.desktop.behaviors.RotateBehavior
import com.exokomodo.komodo.ecs.systems.MoveAndRotateSpriteSystem
import com.exokomodo.komodo.desktop.components.{RotationComponent, VelocityComponent}
import com.exokomodo.komodo.ecs.systems.AsyncBehaviorSystem
import com.exokomodo.komodo.desktop.behaviors.AsyncTestBehavior
import com.exokomodo.komodo.lib.Vector3

object Example extends App {
  val entity = new Entity

  Game.registerSystem(new AsyncBehaviorSystem)
  Game.registerSystem(new RenderSpriteSystem)
  Game.registerSystem(new MoveAndRotateSpriteSystem)
  Game.registerEntity(entity)

  Game.registerComponent(
    AsyncTestBehavior.apply(
      entity,
    )
  )
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
      "badlogic.jpg",
    )
  )
  Game.registerComponent(
    VelocityComponent.apply(
      entity,
      Vector3.apply(100, 0, 0),
    )
  )
  Game.registerComponent(
    RotationComponent.apply(
      entity,
      90,
    )
  )

  Game.run()
}
