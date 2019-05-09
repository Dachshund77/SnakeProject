package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SoundOptions extends Application { //TODO kill on main window close

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SoundOptions.fxml"));
        primaryStage.setTitle("Sound Options");
        Scene Scene = new Scene(root,400,300);
        primaryStage.setScene(Scene);
        primaryStage.show();
        Scene.getRoot().requestFocus();
    }
}
