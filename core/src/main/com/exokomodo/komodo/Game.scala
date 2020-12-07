package com.exokomodo.komodo

import com.exokomodo.komodo.ecs.components.BaseComponent
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.systems
import com.exokomodo.komodo.ecs.systems.{BaseSystem, SystemId}

import com.badlogic.gdx.backends.lwjgl.{
  LwjglApplication => Application,
  LwjglApplicationConfiguration => ApplicationConfiguration,
}

object Game {
  private val _engine: Engine = new Engine
  private var _isEditor: Boolean = false
  private var _isRunning: Boolean = false

  def executeOnSystems(fn: (SystemId, systems.System) => Unit): Unit = _engine.executeOnSystems(fn)

  def isRunning: Boolean = _isRunning

  def quit: Unit = {
    import com.badlogic.gdx.Gdx

    _isRunning = false
    Gdx.app.exit()
    System.exit(0)
  }

  def registerComponent(component: BaseComponent): Unit = _engine.registerComponent(component)

  def registerEntity(entity: Entity): Unit = _engine.registerEntity(entity)

  def registerSystem(system: BaseSystem): Unit = _engine.registerSystem(system)

  def reset(): Unit = {
    _engine.reset()
  }

  def run(): Unit = {
    _isEditor = false
    _runCommon()
  }

  def runInEditor(): Unit = {
    _isEditor = true
    _runCommon()
  }

  private def _runCommon(): Application = {
    _isRunning = true
    val config = new ApplicationConfiguration
    new Application(_engine, config)
  }
}
