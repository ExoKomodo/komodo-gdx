package com.exokomodo.komodo.editor.views

import com.exokomodo.komodo.Game
import com.exokomodo.komodo.editor.views.KomodoView
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage

class MainView(
  private val _stage: Stage,
) extends KomodoView {
  _initialize()

  override def show(): Unit = {
    _stage.show()
  }

  override def stop: Unit = {
    if (Game.isRunning)
      Game.quit
  }

  private def _initialize(): Unit = {
    val root = _loadNode[VBox](getClass, _sceneXmlPath)    
    val scene = new Scene(root, 300, 250)
    scene.getStylesheets().add(
      _loadStylesheet(getClass, _sceneStylesPath)
    )
    
    _stage.setTitle("Komodo Engine Editor")
    _stage.setScene(scene)
    _stage.show()
  }
}