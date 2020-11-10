package com.exokomodo.komodo.desktop

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.exokomodo.komodo.Engine

object DesktopLauncher extends App {
  val config = new LwjglApplicationConfiguration
  new LwjglApplication(new Engine, config)
}
