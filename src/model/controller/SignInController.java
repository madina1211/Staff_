package model.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

import static constants.Constants.DATABASE_PASSWORD;
import static constants.Constants.DATABASE_USER;

public class SignInController {

    @FXML
    private JFXTextField usernameJFXTextField;

    @FXML
    private JFXPasswordField passwordJFXPasswordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private Label messageLabel;

    @FXML
    void signInButtonOnAction(ActionEvent event) {
        if(usernameJFXTextField.getText().equals(DATABASE_USER) &&
        passwordJFXPasswordField.getText().equals(DATABASE_PASSWORD)){
            messageLabel.setVisible(false);
            loadPage(1100, 600, "src/model/view/managerMenu", "Manager");
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.close();
        } else {
            messageLabel.setVisible(true);
        }
    }


    private void loadPage (double height, double width, String page, String setTitle){
        Parent root = null;
        try {
            URL url = new File(page + ".fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            Stage registerStage = new Stage();
            registerStage.setTitle(setTitle);
            registerStage.initStyle(StageStyle.DECORATED);
            registerStage.setScene(new Scene(root, height, width));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

}