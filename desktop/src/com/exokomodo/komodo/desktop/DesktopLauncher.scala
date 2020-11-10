package com.exokomodo.komodo.desktop

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.exokomodo.komodo.Engine

object DesktopLauncher {
  def main(arg: Array[String]): Unit = {
    val config = new LwjglApplicationConfiguration
    new LwjglApplication(new Engine, config)
  }
}
