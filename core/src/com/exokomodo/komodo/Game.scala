package com.exokomodo.komodo

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import com.exokomodo.komodo.ecs.components.{BaseComponent, SpriteComponent, TransformComponent}
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.systems
import com.exokomodo.komodo.ecs.systems.{BaseSystem, RenderSpriteSystem, SystemId}

object Game {
  private val _engine: Engine = new Engine

  def executeOnSystems(fn: (SystemId, systems.System) => Unit): Unit = _engine.executeOnSystems(fn)

  def registerComponent(component: BaseComponent): Unit = _engine.registerComponent(component)

  def registerEntity(entity: Entity): Unit = _engine.registerEntity(entity)

  def registerSystem(system: BaseSystem): Unit = _engine.registerSystem(system)

  def run(): Unit = {
    val config = new Lwjgl3ApplicationConfiguration
    new Lwjgl3Application(_engine, config)
  }
}
