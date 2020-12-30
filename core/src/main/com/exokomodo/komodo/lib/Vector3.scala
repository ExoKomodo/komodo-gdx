package com.exokomodo.komodo.lib

import com.badlogic.gdx.math.{Vector3 => GdxVector3}
import com.badlogic.gdx.Gdx

object Vector3 {
  val One = Vector3.apply(1, 1, 1)
  val X = Vector3.apply(GdxVector3.X)
  val Y = Vector3.apply(GdxVector3.Y)
  val Z = Vector3.apply(GdxVector3.Z)
  val Zero = Vector3.apply(GdxVector3.Zero)

  def apply(source: Vector3): Vector3 = Vector3.apply(source.x, source.y, source.z)
  def apply(x: Float = 0, y: Float = 0, z: Float = 0): Vector3 = new Vector3(new GdxVector3(x, y, z))
  private def apply(source: GdxVector3): Vector3 = Vector3.apply(source.x, source.y, source.z)
}

case class Vector3(
  private val _inner: GdxVector3 = GdxVector3.Zero,
) {
  val x = _inner.x
  val y = _inner.y
  val z = _inner.z

  def rotate(rotationX: Float, rotationY: Float, rotationZ: Float, degrees: Float): Vector3 =
    rotate(Vector3.apply(rotationX, rotationY, rotationZ), degrees)

  def rotate(rotationAxis: Vector3, degrees: Float): Vector3 =
    Vector3.apply(
      _inner.cpy.rotate(rotationAxis._inner, degrees)
    )
  
  def rotateRadians(rotationX: Float, rotationY: Float, rotationZ: Float, radians: Float): Vector3 =
    rotateRadians(Vector3.apply(rotationX, rotationY, rotationZ), radians)

  def rotateRadians(rotationAxis: Vector3, radians: Float): Vector3 =
    Vector3.apply(
      _inner.cpy.rotateRad(rotationAxis._inner, radians)
    )

  def scale(
    factor: Float,
  ): Vector3 = scale(factor, factor, factor)

  def scale(
    factorX: Float,
    factorY: Float,
    factorZ: Float,
  ): Vector3 = scale(Vector3.apply(factorX, factorY, factorZ))

  def scale(factor: Vector3): Vector3 =
    _multiply(this, factor)
  
  def translate(
    deltaX: Float = 0,
    deltaY: Float = 0,
    deltaZ: Float = 0,
  ): Vector3 = translate(Vector3.apply(deltaX, deltaY, deltaZ))

  def translate(delta: Vector3): Vector3 = _add(this, delta)
  
  private def _add(a: Vector3, b: Vector3): Vector3 =
    Vector3.apply(
      a.x + b.x,
      a.y + b.y,
      a.z + b.z,
    )
  
  private def _multiply(a: Vector3, b: Vector3): Vector3 =
    Vector3.apply(
      a.x * b.x,
      a.y * b.y,
      a.z * b.z,
    )
}