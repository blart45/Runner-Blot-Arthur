// add any usefull package line

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;



public class Main extends Application{

  public void start(Stage primaryStage){
    //Initialisation
    primaryStage.setTitle("Runner"); //tittle window
    Group root = new Group(); //initialisation du root
    GameScene game = new GameScene(root,800,400); // creation de la scene
    primaryStage.setScene(game);
    primaryStage.show();
    System.out.println("Use Space to attack and arrow_up to jump");
  }

  public static void main(String[] args) {
    launch(args);
    // write your code here
  }
}

