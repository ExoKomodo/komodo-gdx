package com.exokomodo.komodo

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentId}
import com.exokomodo.komodo.ecs.entities.{Entity, EntityId}
import com.exokomodo.komodo.ecs.systems.BaseSystem
import com.exokomodo.komodo.test.TestSystem

import scala.collection.immutable.HashMap

class Engine extends ApplicationListener {
  private var componentStore: HashMap[ComponentId, BaseComponent] = HashMap.empty[ComponentId, BaseComponent]
  private var entityStore: HashMap[EntityId, Entity] = HashMap.empty[EntityId, Entity]
  private var systemStore: List[BaseSystem] = List.empty[BaseSystem]
  private var img: Option[Texture] = None
  private var spritebatch: Option[SpriteBatch] = None

  def create(): Unit = {
    spritebatch = Some(new SpriteBatch)
    img = Some(new Texture("badlogic.jpg"))
  }

  def render(): Unit = {
    draw()
    update(Gdx.graphics.getDeltaTime())
  }

  def resize(width: Int, height: Int): Unit = {
  }

  def pause(): Unit = {
  }

  def resume(): Unit = {
  }

  def dispose(): Unit = {
    img.get.dispose()
    img = None

    spritebatch.get.dispose()
    spritebatch = None
  }

  def registerComponent(componentToRegister: BaseComponent): Boolean = {
    if (componentStore.contains(componentToRegister.id))
      false
    else {
      componentToRegister.parent match {
        case Some(_) => {
          componentStore.foreachEntry((_, component) => {
            component.parent match {
              case Some(parent) => {
                // Try adding all of the entity's component. If the component is already present on a system, it will not add again.
                // This makes sure that when doing an entity registration check, all available components are in the system.
                // This allows the systems to dynamically register whole entities to run logic on.
                if (parent.id == componentToRegister.parent.get.id) {
                  systemStore.foreach(system => {
                    system.registerComponent(component)
                  })
                }
              }
              case None => ()
            }
          })
          systemStore.foreach(system => {
            system.registerComponent(componentToRegister)
          })
          componentStore += (componentToRegister.id -> componentToRegister)
          true
        }
        case None => false
      }
    }
  }

  def registerEntity(entity: Entity): Boolean = {
    entityStore += (entity.id -> entity)
    // TODO: Register components
    true
  }

  def registerSystem[A <: BaseSystem](system: A): Boolean = {
    // TODO: Register components to system
    true
  }

  protected def clearScreen(color: Color): Unit = {
    clearScreen(color.r, color.g, color.g, color.a)
  }

  protected def clearScreen(red: Float = 0,
                            green: Float = 0,
                            blue: Float = 0,
                            alpha: Float = 1,
                           ): Unit = {
    Gdx.gl.glClearColor(red, green, blue, alpha)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  }

  private def draw(): Unit = {
    clearScreen(Color.CYAN)
    spritebatch match {
      case Some(batch) => {
        batch.begin()
        batch.draw(img.get, 0, 0)
        batch.end()
      }
      case None => return
    }
  }

  private def update(delta: Float): Unit = {

  }
}