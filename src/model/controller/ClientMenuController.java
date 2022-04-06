package model.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

public class ClientMenuController {

    @FXML
    private VBox vbox;

    @FXML
    private Button usefulInfoButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button aboutCompanyButton;

    @FXML
    private Button helpButton;

    @FXML
    void aboutCompanyButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/clientAboutCompany.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void helpButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/clientAboutCompany.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void usefulInfoButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/clientUsefulInfo.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private AnchorPane loadPage (String page){
        AnchorPane pane = null;
        try {
            URL url = new File(page).toURI().toURL();
            pane = FXMLLoader.load(url);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return pane;
    }

}
