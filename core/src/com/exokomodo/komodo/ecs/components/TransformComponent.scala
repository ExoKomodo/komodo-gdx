package com.exokomodo.komodo.ecs.components

import com.badlogic.gdx.math.Vector3

object TransformComponent {
  def apply(position: Vector3 = Vector3.Zero,
            rotation: Vector3 = Vector3.Zero,
            scale: Vector3 = new Vector3(1, 1, 1),
           ): TransformComponent = {
    new TransformComponent(position, rotation, scale)
  }
}

class TransformComponent(var position: Vector3,
                         var rotation: Vector3,
                         var scale: Vector3,
                        ) extends BaseComponent {

}
