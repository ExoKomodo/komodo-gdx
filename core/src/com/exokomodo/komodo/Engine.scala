package com.exokomodo.komodo

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentId}
import com.exokomodo.komodo.ecs.entities.{Entity, EntityId}
import com.exokomodo.komodo.ecs.systems
import com.exokomodo.komodo.ecs.systems.{BaseSystem, DrawableSystem, SystemId, UpdatableSystem}

import scala.collection.immutable.HashMap

object Engine {
  var spriteBatch: Option[SpriteBatch] = None
}

class Engine extends ApplicationListener {
  private var _componentStore: HashMap[ComponentId, BaseComponent] = HashMap.empty[ComponentId, BaseComponent]
  private var _entityStore: HashMap[EntityId, Entity] = HashMap.empty[EntityId, Entity]
  private var _drawSystemStore: HashMap[SystemId, DrawableSystem] = HashMap.empty[EntityId, DrawableSystem]
  private var _updateSystemStore: HashMap[SystemId, UpdatableSystem] = HashMap.empty[EntityId, UpdatableSystem]

  def create(): Unit = {
    Engine.spriteBatch = Some(new SpriteBatch())

    _initialize()
  }

  def render(): Unit = {
    _initialize()

    _draw()
    _update(Gdx.graphics.getDeltaTime())
  }

  def resize(width: Int, height: Int): Unit = {
  }

  def pause(): Unit = {
  }

  def resume(): Unit = {
  }

  def dispose(): Unit = {
  }

  def registerComponent(component: BaseComponent): Boolean = {
    if (_componentStore.contains(component.id))
      false
    else {
      component.parent match {
        case Some(parent) => {
          _componentStore += (component.id -> component)

          executeOnSystems((_: SystemId, system: systems.System) => {
            system.registerComponent(component)
            system.refreshEntityRegistration(parent)
          })

          true
        }
        case None => false
      }
    }
  }

  def registerEntity(entity: Entity): Boolean = {
    _entityStore += (entity.id -> entity)

    executeOnSystems((_: SystemId, system: systems.System) => {
      system.refreshEntityRegistration(entity)
    })
    true
  }

  def registerSystem[A <: BaseSystem](system: A): Boolean = {
    system match {
      case drawable: DrawableSystem => _drawSystemStore += (drawable.id -> drawable)
      case updatable: UpdatableSystem => _updateSystemStore += (updatable.id -> updatable)
    }
    _entityStore.foreachEntry((_, entity) => system.refreshEntityRegistration(entity))
    true
  }

  def executeOnSystems(fn: (SystemId, systems.System) => Unit): Unit = {
    _drawSystemStore.foreachEntry(fn)
    _updateSystemStore.foreachEntry(fn)
  }

  def unregisterComponent(componentId: ComponentId): Boolean = {
    _componentStore.get(componentId) match {
      case Some(component) => unregisterComponent(component)
      case None => false
    }
  }

  def unregisterComponent(component: BaseComponent): Boolean = {
    executeOnSystems((_: SystemId, system: systems.System) => {
      system.unregisterComponent(component)
      system.refreshEntityRegistration(component.parent.get)
    })
    true
  }

  def unregisterEntity(entityId: EntityId): Boolean = {
    _entityStore.get(entityId) match {
      case Some(entity) => unregisterEntity(entity)
      case None => false
    }
  }

  def unregisterEntity(entity: Entity): Boolean = {
    val components = for ((_, component) <- _componentStore if component.parent.get.id == entity.id) yield component
    components.foreach(component => unregisterComponent(component))
    true
  }

  def unregisterSystem(systemId: SystemId): Boolean = {
    _drawSystemStore.get(systemId) match {
      case Some(system) => unregisterSystem(system)
      case None => {
        _updateSystemStore.get(systemId) match {
          case Some(system) => unregisterSystem(system)
          case None => false
        }
      }
    }
  }

  def unregisterSystem(system: systems.System): Boolean = {
    _drawSystemStore -= system.id
    _updateSystemStore -= system.id
    true
  }

  protected def _clearScreen(color: Color): Unit = {
    _clearScreen(color.r, color.g, color.g, color.a)
  }

  protected def _clearScreen(
                              red: Float = 0,
                              green: Float = 0,
                              blue: Float = 0,
                              alpha: Float = 1,
                           ): Unit = {
    Gdx.gl.glClearColor(red, green, blue, alpha)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  }

  private def _draw(): Unit = {
    _clearScreen(Color.CYAN)

    _drawSystemStore.foreachEntry((_, system) => system.draw())
  }

  private def _initialize(): Unit = {
    executeOnSystems((_: SystemId, system: systems.System) => system.initialize())
  }

  private def _update(delta: Float): Unit = {
    _updateSystemStore.foreachEntry((_, system) => system.update(delta))
  }
}