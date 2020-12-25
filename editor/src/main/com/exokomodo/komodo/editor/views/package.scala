package com.exokomodo.komodo.editor

import java.net.URL
import javafx.scene.Node
import javafx.fxml.FXMLLoader

package object views {
    trait KomodoView {
        def show(): Unit
        def stop: Unit
        
        protected final def _sceneXmlPath = s"${_resourceName}/scene.fxml"
        protected final def _sceneStylesPath = s"${_resourceName}/styles.css"
        
        protected final def _loadNode[A <: Node](c: Class[_], xmlResourcePath: String): A = {
            new FXMLLoader(c.getResource(xmlResourcePath)).load[A]()
        }
        protected final def _loadStylesheet(c: Class[_], styleSheetResourcePath: String): String = {
            c.getResource(styleSheetResourcePath).toExternalForm()
        }
        private final def _resourceName = getClass().getSimpleName().replace("View", "")
    }
}
