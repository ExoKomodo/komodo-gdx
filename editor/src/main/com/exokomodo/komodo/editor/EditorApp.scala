package com.exokomodo.komodo.editor

import com.exokomodo.komodo.editor.views.MainView
import javafx.application.Application
import javafx.stage.Stage

object EditorApp {
    def apply(): EditorApp = {
        new EditorApp
    }
}

class EditorApp extends Application {
    private var _mainView: Option[MainView] = None

    def run(args: Array[String]): Unit = {
        Application.launch(args:_*)
    }

    def start(stage: Stage): Unit = {
        _mainView = Some(new MainView(stage))
        _mainView.get.show
    }

    override def stop(): Unit = {
        _mainView match {
            case Some(mainView) => mainView.stop
            case None => ()
        }

        super.stop()
    }
}