package es.QuePasApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;

public class ChatClient extends Application {

    private static final String SERVER_ADDRESS = "92.191.7.33";
    private static final int SERVER_PORT = 53463;
    private TextArea messageArea;
    private TextField inputField;
    private PrintWriter out;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat Client");

        BorderPane borderPane = new BorderPane();
        messageArea = new TextArea();
        messageArea.setEditable(false);
        inputField = new TextField();
        Button sendButton = new Button("Send");
        
        sendButton.setOnAction(e -> sendMessage());

        VBox bottomLayout = new VBox(10, inputField, sendButton);
        borderPane.setCenter(messageArea);
        borderPane.setBottom(bottomLayout);

        Scene scene = new Scene(borderPane, 960, 540);
        primaryStage.setScene(scene);
        primaryStage.show();

        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            out = new PrintWriter(output, true);

            // Listen for server messages
            new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        final String finalMessage = message;
                        javafx.application.Platform.runLater(() -> messageArea.appendText(finalMessage + "\n"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        if (out != null) {
            String message = inputField.getText();
            out.println(message);
            inputField.clear();
        }
    }
}

