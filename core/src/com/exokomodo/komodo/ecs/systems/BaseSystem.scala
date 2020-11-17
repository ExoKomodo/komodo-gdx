package com.exokomodo.komodo.ecs.systems

import com.exokomodo.komodo.ecs.components.{
  BaseComponent,
  ComponentTypeId,
  getComponentTypeId,
}
import com.exokomodo.komodo.ecs.entities.EntityId

import scala.collection.immutable.HashMap

abstract class BaseSystem extends System {
  protected var _registeredTypes: Set[ComponentTypeId]
  protected var _entityToComponents: HashMap[EntityId, List[BaseComponent]] = HashMap.empty[EntityId, List[BaseComponent]]

  private var _isInitialized: Boolean = false
  def isInitialized = _isInitialized
  def isInitialized_=(v: Boolean): Unit = { _isInitialized = v }

  override def initialize(): Unit = {
    if (isInitialized) return

    _entityToComponents.foreachEntry((_, components) => {
      components.foreach(component => component.initialize())
    })

    isInitialized = true
  }

  override def registerComponent(component: BaseComponent): Boolean = {
    if (!_registeredTypes.contains(getComponentTypeId(component.getClass))) {
      false
    } else {
      component.parent match {
        case None => false
        case Some(parent) => {
          if (!_entityToComponents.contains(parent.id)) {
            _entityToComponents += (parent.id -> List(component))
            true
          } else {
            val components = _entityToComponents(parent.id)
            if (components.contains(component)) {
              false
            } else {
              _entityToComponents = _entityToComponents.updated(
                parent.id,
                component :: components,
              )
              true
            }
          }
        }
      }
    }
  }

  protected def _findComponentByTypeId[A <: BaseComponent](
                                        components: List[BaseComponent],
                                        c: Class[A],
                                      ): Option[A] = {
    val typeId = getComponentTypeId(c)
    components.find(
      component => getComponentTypeId(component.getClass) == typeId
    ).asInstanceOf[Option[A]]
  }
}
