package com.feint.fx

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class App: Application(){
    override fun start(primaryStage: Stage?) {
        val root= FXMLLoader.load<Parent>(javaClass.classLoader.getResource("main.fxml"))
        primaryStage!!.title="Start"
        primaryStage.scene= Scene(root,600.0,400.0)
        primaryStage.show()
    }

}

fun main(args: Array<String>) {
    Application.launch(App::class.java,*args)
}
