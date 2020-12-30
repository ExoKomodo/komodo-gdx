package com.exokomodo.komodo.ecs.components

import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.components
import com.exokomodo.komodo.lib.Vector3
import com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController.Transform

object TransformComponent {
  final val typeId = "TransformComponent"

  def apply(
    parent: Entity,
    position: Vector3 = Vector3.Zero,
    rotation: Vector3 = Vector3.Zero,
    scale: Vector3 = Vector3.apply(1, 1, 1),
  ): TransformComponent =
    new TransformComponent(
      Some(parent),
      position,
      rotation,
      scale,
    )
}

@TypeId(id = TransformComponent.typeId)
sealed class TransformComponent(
  override val parent: Option[Entity],
  private var _position: Vector3,
  private var _rotation: Vector3,
  private var _scale: Vector3,
) extends BaseComponent {
  def position = _position
  def position_=(v: Vector3): Unit = { _position = v }

  def rotation = _rotation
  def rotation_=(v: Vector3): Unit = { _rotation = v }

  def scale = _scale
  def scale_=(v: Vector3): Unit = { _scale = v }

  override def initialize(): Unit =
    super.initialize()
  
  def rotate(degreeX: Float, degreeY: Float, degreeZ: Float): TransformComponent =
    rotate(Vector3.apply(degreeX, degreeY, degreeZ))

  def rotate(degrees: Vector3): TransformComponent = {
    rotation = rotation.translate(degrees)
    this
  }

  def scale(
    factor: Float,
  ): TransformComponent = {
    scale = scale.scale(factor, factor, factor)
    this
  }

  def scale(
    factorX: Float,
    factorY: Float,
    factorZ: Float,
  ): TransformComponent = {
    scale = scale.scale(Vector3.apply(factorX, factorY, factorZ))
    this
  }

  def scale(factor: Vector3): TransformComponent = {
    scale = scale.scale(factor)
    this
  }
  
  def translate(
    deltaX: Float = 0,
    deltaY: Float = 0,
    deltaZ: Float = 0,
  ): TransformComponent = {
    position = position.translate(Vector3.apply(deltaX, deltaY, deltaZ))
    this
  }

  def translate(delta: Vector3): TransformComponent = {
    position = position.translate(delta)
    this
  }
}
