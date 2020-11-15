package com.exokomodo.komodo.ecs.components

object SpriteComponent {
  def apply(): SpriteComponent = {
    new SpriteComponent
  }
}

class SpriteComponent extends Drawable2dComponent {

}
