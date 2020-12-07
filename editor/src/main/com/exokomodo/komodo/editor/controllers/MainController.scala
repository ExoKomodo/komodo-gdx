package com.exokomodo.komodo.editor.controllers

import com.exokomodo.komodo.Game
import com.exokomodo.komodo.editor.controllers.KomodoController
import com.exokomodo.komodo.editor.views.GameView
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label


object MainController {
    var gameView: Option[GameView] = None
}

class MainController extends KomodoController {
  @FXML private var _label: Label = _
  
  override def initialize(): Unit = {
    val javaVersion = System.getProperty("java.version")
    val javafxVersion = System.getProperty("javafx.version")
    _label.setText(s"Hello, JavaFX $javafxVersion\nRunning on Java $javaVersion.")
  }

  def onDemoPress(event: ActionEvent): Unit = {
    if (Game.isRunning)
      MainController.gameView match {
        case Some(gameView) => gameView.setupDemo()
        case None => ()
      }
  }

  def onResetPress(event: ActionEvent): Unit = {
    if (Game.isRunning)
      Game.reset()
  }
  
  def onStartPress(event: ActionEvent): Unit = {
    if (!Game.isRunning)
      Platform.runLater(new Runnable {
          override def run(): Unit = {
              val view = new GameView()
              MainController.gameView = Some(view)
              view.show
          }
      })
  }
}
