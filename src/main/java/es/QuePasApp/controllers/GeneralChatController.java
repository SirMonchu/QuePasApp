package es.QuePasApp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import es.QuePasApp.Session;

public class GeneralChatController {

    @FXML
    private TextArea TextFieldtxt;
    @FXML
    private TextField inputField; 
    @FXML
    private Button sendButton;

    private static final String SERVER_ADDRESS = "92.191.7.33";
    private static final int SERVER_PORT = 53463;
    private PrintWriter out;
    private int roomNumber;
    private Socket socket;

    public GeneralChatController(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public GeneralChatController() {
        this.roomNumber = 0; // default room
    }

    public void initialize() {
        connectToServer();
        inputField.setOnAction(e -> sendMessage());
    }

    private void connectToRoom(int newRoomNumber) {
        disconnectFromServer();  // Desconectarse de la sala actual si está conectado
        roomNumber = newRoomNumber;
        connectToServer();
    }
    
    private void disconnectFromServer() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            out = new PrintWriter(output, true);

            // First, send room number
            out.println(roomNumber);
            String username = (Session.getCurrentUser() != null) ? Session.getCurrentUser() : "Anónimo";
            out.println(username);
            // Handle sending messages
            sendButton.setOnAction(e -> sendMessage());

            // Listen for server messages
            new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        final String finalMessage = message;
                        javafx.application.Platform.runLater(() -> TextFieldtxt.appendText(finalMessage + "\n"));
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
        if (out != null && !inputField.getText().trim().isEmpty()) {
            // Obtener la hora actual en formato HH:mm
            String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            
            if (inputField.getText().trim().equals("/users")) {
                out.println("/getUsers:" + roomNumber);
            } else {
                String username = (Session.getCurrentUser() != null) ? Session.getCurrentUser() : "Anónimo";
                String message = "[" + currentTime + "] " + username + ": " + inputField.getText();
                out.println(message);
            }
            inputField.clear();
        }
    }

    
    @FXML
    private void onGeneralRoomButtonPressed() {
    	TextFieldtxt.clear();
        connectToRoom(0);
    }
    
    @FXML
    private void onTechRoomButtonPressed() {
    	TextFieldtxt.clear();
    	connectToRoom(1);
    }

    public void PruebaTexto(MouseEvent mouseEvent) {
        
    }
}
