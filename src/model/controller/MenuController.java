package model.controller;

import com.jfoenix.controls.JFXButton;
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

public class MenuController {

    @FXML
    private JFXButton clientButton;

    @FXML
    private JFXButton managerButton;

    @FXML
    void clientButtonOnAction(ActionEvent event) {

        loadPage(900, 600, "src/model/view/clientMenu", "ClientMenu");
        Stage stage = (Stage) clientButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void managerButtonOnAction(ActionEvent event) {

        loadPage(600, 400, "src/model/view/signIn", "Manager");
        Stage stage = (Stage) managerButton.getScene().getWindow();
        stage.close();


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
