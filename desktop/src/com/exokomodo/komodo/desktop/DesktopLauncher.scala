package com.exokomodo.komodo.desktop

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.exokomodo.komodo.GdxEngine

object DesktopLauncher extends App {
  val config = new LwjglApplicationConfiguration
  new LwjglApplication(new GdxEngine, config)
}
