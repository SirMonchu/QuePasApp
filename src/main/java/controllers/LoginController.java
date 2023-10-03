package controllers;


import java.io.IOException;

import es.QuePasApp.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class LoginController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField UserNAmetxt;


    @FXML
    protected void onHelloButtonClick() throws IOException {
            UserMenu();
    }
    public void UserMenu() throws IOException {
    	Session.setCurrentUser(UserNAmetxt.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/Salas.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 600.0, 400.0);
        SalasController controlador = (SalasController)fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }
    
}