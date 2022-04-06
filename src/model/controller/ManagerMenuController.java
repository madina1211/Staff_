package model.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;

public class ManagerMenuController {


    @FXML
    private VBox vbox;

    @FXML
    private Button tableButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button queryButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button procedureButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void procedureButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void queryButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void reportButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/managerReport.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void tableButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/managerTable.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void viewButtonOnAction(ActionEvent event) {
        try {
            anchorPane.getChildren().setAll(loadPage("src/model/view/.fxml"));
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
