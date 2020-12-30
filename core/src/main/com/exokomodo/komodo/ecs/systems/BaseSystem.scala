package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentTypeId, getComponentTypeId}
import com.exokomodo.komodo.ecs.entities.{Entity, EntityId}

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

abstract class BaseSystem extends System {
  protected override final val _entityToComponents: HashMap[EntityId, ListBuffer[BaseComponent]] = HashMap.empty[EntityId, ListBuffer[BaseComponent]]
  protected override final val _uninitializedComponents: ListBuffer[BaseComponent] = ListBuffer.empty[BaseComponent]

  override final def findComponentByParent[A <: BaseComponent](entity: Entity, c: Class[A]): Option[A] =
    findComponentByParent(entity, getComponentTypeId(c))

  override final def findComponentByParent[A <: BaseComponent](entity: Entity, componentTypeId: ComponentTypeId): Option[A] = {
    _entityToComponents.get(entity.id) match {
      case Some(components) => _findComponentByTypeId[A](components, componentTypeId)
      case None => None
    }
  }

  override def initialize(): Unit = {
    _uninitializedComponents.foreach(component => component.initialize())
    _uninitializedComponents.clear()
    isInitialized = true
  }

  override final def refreshEntityRegistration(entity: Entity): Unit = {
    _entityToComponents.get(entity.id) match {
      case Some(components) => {
        var missingType = false
        _registeredTypes.foreach(componentTypeId => {
          _findComponentByTypeId(components, componentTypeId) match {
            case Some(_) => ()
            case None => missingType = true
          }
        })
        if (missingType)
          return
        _entityToComponents -= entity.id

        components.foreach(component => registerComponent(component))
      }
      case None => ()
    }
  }

  override final def registerComponent(component: BaseComponent): Boolean = {
    if (!_registeredTypes.contains(getComponentTypeId(component.getClass)))
      false
    else {
      component.parent match {
        case None => false
        case Some(parent) => {
          if (!_entityToComponents.contains(parent.id))
            _entityToComponents += (parent.id -> ListBuffer.empty[BaseComponent])

          val components = _entityToComponents(parent.id)
          if (components.contains(component))
            false
          else {
            _entityToComponents.update(
              parent.id,
              components += component,
            )
            if (!component.isInitialized)
              _uninitializedComponents += component
            true
          }
        }
      }
    }
  }

  override final def deregisterComponent(component: BaseComponent): Boolean = {
    val parent = component.parent.get
    _entityToComponents.get(parent.id) match {
      case Some(components) => {
        _entityToComponents.update(
          parent.id,
          components -= component,
        )
        component.parent = None
        true
      }
      case None => false
    }
  }

  protected final def _findComponentByClass[A <: BaseComponent](
    components: ListBuffer[BaseComponent],
    c: Class[A],
  ): Option[A] = {
    _findComponentByTypeId(components, getComponentTypeId(c))
  }

  protected final def _findComponentByTypeId[A <: BaseComponent](
    components: ListBuffer[BaseComponent],
    typeId: ComponentTypeId,
  ): Option[A] = {
    components.find(
      component => getComponentTypeId(component.getClass) == typeId
    ).asInstanceOf[Option[A]]
  }
}
