package UI;

import Domain.Sound.SoundPlayer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Snake Game");
        Scene Scene = new Scene(root,600,600);
        primaryStage.setScene(Scene);
        primaryStage.show();
        Scene.getRoot().requestFocus();

        //If the main Stage is closing then we want to save preferences
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                SoundPlayer.getInstance().savePreferences();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
