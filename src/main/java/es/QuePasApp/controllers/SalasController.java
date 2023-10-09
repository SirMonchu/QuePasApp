package es.QuePasApp.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SalasController {

    @FXML
    private Button Joinbtt;
    @FXML
    private Button NewChatbtt;
    @FXML
    private Button JoinChatbtt;

    public void JoinGeneral() throws IOException{
        GeneralChat();
    }
    public void CreateNewChat() throws IOException{
        CreateChat();
    }
    public void JoinAChat() throws IOException{
        JoinChat();
    }
    
    public void GeneralChat() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SalasController.class.getResource("/GeneralChat.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load(), 600.0, 400.0);
            GeneralChatController controlador = (GeneralChatController) fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void JoinChat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SalasController.class.getResource("/GeneralChat.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), 600.0, 400.0);
        GeneralChatController controlador = (GeneralChatController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void CreateChat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/CreateChat.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 600.0, 400.0);
        CreateChatController controlador = (CreateChatController)fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }
}
