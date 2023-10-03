package es.QuePasApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml")); // Update the path to your Login.fxml
        Scene scene = new Scene(root, 960, 540);
        primaryStage.setTitle("Chat Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
