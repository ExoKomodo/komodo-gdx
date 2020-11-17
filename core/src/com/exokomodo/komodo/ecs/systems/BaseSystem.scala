package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{BaseComponent, ComponentTypeId, getComponentTypeId}
import com.exokomodo.komodo.ecs.entities.{Entity, EntityId}

import scala.collection.immutable.HashMap

abstract class BaseSystem extends System {
  protected override final var _entityToComponents: HashMap[EntityId, List[BaseComponent]] = HashMap.empty[EntityId, List[BaseComponent]]
  protected override final var _uninitializedComponents: List[BaseComponent] = List.empty[BaseComponent]

  override final def findComponentByParent[A <: BaseComponent](entity: Entity, c: Class[A]): Option[A] = {
    findComponentByParent(entity, getComponentTypeId(c))
  }

  override final def findComponentByParent[A <: BaseComponent](entity: Entity, componentTypeId: ComponentTypeId): Option[A] = {
    _entityToComponents.get(entity.id) match {
      case Some(components) => _findComponentByTypeId[A](components, componentTypeId)
      case None => None
    }
  }

  override def initialize(): Unit = {
    _uninitializedComponents.foreach(component => component.initialize())
    _uninitializedComponents = List.empty[BaseComponent]
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
        _entityToComponents = _entityToComponents - entity.id

        components.foreach(component => registerComponent(component))
      }
      case None => ()
    }
  }

  protected final def _findComponentByClass[A <: BaseComponent](
                                                                  components: List[BaseComponent],
                                                                  c: Class[A],
                                                                ): Option[A] = {
    _findComponentByTypeId(components, getComponentTypeId(c))
  }

  protected final def _findComponentByTypeId[A <: BaseComponent](
                                                                  components: List[BaseComponent],
                                                                  typeId: ComponentTypeId,
                                                                ): Option[A] = {
    components.find(
      component => getComponentTypeId(component.getClass) == typeId
    ).asInstanceOf[Option[A]]
  }

  override final def registerComponent(component: BaseComponent): Boolean = {
    if (!_registeredTypes.contains(getComponentTypeId(component.getClass)))
      false
    else {
      component.parent match {
        case None => false
        case Some(parent) => {
          if (!_entityToComponents.contains(parent.id))
            _entityToComponents += (parent.id -> List.empty[BaseComponent])

          val components = _entityToComponents(parent.id)
          if (components.contains(component)) false
          else {
            _entityToComponents = _entityToComponents.updated(
              parent.id,
              component :: components,
            )
            if (!component.isInitialized)
              _uninitializedComponents = component :: _uninitializedComponents
            true
          }
        }
      }
    }
  }

  override final def unregisterComponent(component: BaseComponent): Boolean = {
    true
  }
}
