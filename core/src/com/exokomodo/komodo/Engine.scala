package com.exokomodo.komodo

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentId}
import com.exokomodo.komodo.ecs.entities.{Entity, EntityId}
import com.exokomodo.komodo.ecs.systems.{BaseSystem, DrawableSystem, UpdatableSystem}

import scala.collection.immutable.HashMap

object Engine {
  var spriteBatch: Option[SpriteBatch] = None
}

class Engine extends ApplicationListener {
  private var _componentStore: HashMap[ComponentId, BaseComponent] = HashMap.empty[ComponentId, BaseComponent]
  private var _entityStore: HashMap[EntityId, Entity] = HashMap.empty[EntityId, Entity]
  private var _drawSystemStore: List[DrawableSystem] = List.empty[DrawableSystem]
  private var _updateSystemStore: List[UpdatableSystem] = List.empty[UpdatableSystem]

  def create(): Unit = {
    Engine.spriteBatch = Some(new SpriteBatch())

    _drawSystemStore.foreach(system => system.initialize())
    _updateSystemStore.foreach(system => system.initialize())
  }

  def render(): Unit = {
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

  def registerComponent(componentToRegister: BaseComponent): Boolean = {
    if (_componentStore.contains(componentToRegister.id))
      false
    else {
      componentToRegister.parent match {
        case Some(_) => {
          _componentStore.foreachEntry((_, component) => {
            component.parent match {
              case Some(parent) => {
                // Try adding all of the entity's component. If the component is already present on a system, it will not add again.
                // This makes sure that when doing an entity registration check, all available components are in the system.
                // This allows the systems to dynamically register whole entities to run logic on.
                if (parent.id == componentToRegister.parent.get.id) {
                  _drawSystemStore.foreach(system => {
                    system.registerComponent(component)
                  })
                  _updateSystemStore.foreach(system => {
                    system.registerComponent(component)
                  })
                }
              }
              case None => ()
            }
          })
          _drawSystemStore.foreach(system => {
            system.registerComponent(componentToRegister)
          })
          _updateSystemStore.foreach(system => {
            system.registerComponent(componentToRegister)
          })
          _componentStore += (componentToRegister.id -> componentToRegister)
          true
        }
        case None => false
      }
    }
  }

  def registerEntity(entity: Entity): Boolean = {
    _entityStore += (entity.id -> entity)
    true
  }

  def registerSystem[A <: BaseSystem](system: A): Boolean = {
    system match {
      case drawable: DrawableSystem => _drawSystemStore = drawable :: _drawSystemStore
      case updatable: UpdatableSystem => _updateSystemStore = updatable :: _updateSystemStore
    }
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

    _drawSystemStore.foreach(system => system.draw())
  }

  private def _update(delta: Float): Unit = {
    _updateSystemStore.foreach(system => system.update(delta))
  }
}