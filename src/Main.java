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

    //
    Scene firstScene = new Scene(root,600,400);
    primaryStage.setScene(firstScene);//in a window which are scene length and width
    Button btn=new Button("<PLAY>");
    root.getChildren().add(btn);
    btn.setLayoutX(275);
    btn.setLayoutY(175);
    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        GameScene game = new GameScene(root,800,400); // creation de la scene
        primaryStage.setScene(game);
      }
    });
//    GameScene game = new GameScene(root,800,400); // creation de la scene
//    primaryStage.setScene(game);
//    primaryStage.show();
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
    // write your code here
  }
}

