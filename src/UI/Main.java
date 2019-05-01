package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene Scene = new Scene(root,600,600);
        primaryStage.setScene(Scene);
        primaryStage.show();
        Scene.getRoot().requestFocus();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
