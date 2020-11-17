package com.exokomodo.komodo.ecs

import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentTypeId}
import com.exokomodo.komodo.ecs.entities.{Entity, EntityId}

import scala.collection.immutable.HashMap

package object systems {
  type SystemId = Int

  private var _nextId: SystemId = 0

  def nextId(): SystemId = {
    _nextId += 1
    _nextId
  }

  trait System {
    val id: SystemId = nextId()

    protected val _registeredTypes: Set[ComponentTypeId]
    protected var _entityToComponents: HashMap[EntityId, List[BaseComponent]]
    protected var _uninitializedComponents: List[BaseComponent]

    private var _isInitialized: Boolean = false
    def isInitialized = _isInitialized
    def isInitialized_=(v: Boolean): Unit = { _isInitialized = v }

    def findComponentByParent[A <: BaseComponent](parent: Entity, c: Class[A]): Option[A]
    def findComponentByParent[A <: BaseComponent](parent: Entity, componentTypeId: ComponentTypeId): Option[A]
    def initialize(): Unit
    def refreshEntityRegistration(entity: Entity): Unit
    def registerComponent(component: BaseComponent): Boolean
    def unregisterComponent(component: BaseComponent): Boolean
  }

  trait DrawableSystem extends System {
    def draw(): Unit
  }

  trait UpdatableSystem extends System {
    def update(delta: Float): Unit
  }
}
