package com.exokomodo.komodo

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.exokomodo.komodo.ecs.{BaseComponent, BaseSystem}
import com.exokomodo.komodo.test.{TestComponent, TestSystem}

class GdxEngine extends ApplicationListener with Engine {
  private var batch: SpriteBatch = null
  private var img: Texture = null

  def create(): Unit = {
    batch = new SpriteBatch
    img = new Texture("badlogic.jpg")
    TestSystem.test()
  }

  def render(): Unit = {
    Gdx.gl.glClearColor(1, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    batch.draw(img, 0, 0)
    batch.end()
  }

  def resize(width: Int, height: Int): Unit = {
  }

  def pause(): Unit = {
  }

  def resume(): Unit = {
  }

  def dispose(): Unit = {
  }

  override def registerSystem[A <: BaseSystem](system: A): Boolean = ???
}