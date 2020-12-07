package com.exokomodo.komodo.editor

package object views {
    trait KomodoView {
        def show: Unit
        def stop: Unit
        
        def sceneXmlPath = s"${getClass().getSimpleName()}/scene.fxml"
        def sceneStylesPath = s"${getClass().getSimpleName()}/styles.css"
    }
}
