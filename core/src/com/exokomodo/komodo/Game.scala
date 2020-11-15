package com.exokomodo.komodo

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.systems.BaseSystem

object Game {
  private val engine: Engine = new Engine

  def registerEntity(entity: Entity): Unit = {
    engine.registerEntity(entity)
  }

  def registerSystem(system: BaseSystem): Unit = {
    engine.registerSystem(system)
  }

  def run(): Unit = {
    val config = new LwjglApplicationConfiguration
    new LwjglApplication(engine, config)
  }
}
