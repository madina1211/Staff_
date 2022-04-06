package model.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import staff.Education;
import staff.Staff;
import staff.Worker;

import java.io.*;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.Constants.*;

public class ManagerReportController implements Initializable {

    @FXML
    private TableView<Worker> workerTableView;

    @FXML
    private TableColumn<Worker, String> workerTableColumn;

    @FXML
    private JFXComboBox<String> departmentChoiceBox;

    @FXML
    private JFXButton showButton;

    @FXML
    private JFXComboBox<String> educationChoiceBox;

    @FXML
    private ImageView excelImageView;

    @FXML
    private ImageView excelImageView1;

    @FXML
    private TableView<Worker> workExperienceTableView;

    @FXML
    private TableColumn<Worker, String> fioTableColumn;

    @FXML
    private TableColumn<Worker, Integer> experienceTableColumn;

    private Connection connection;


    private ObservableList<String> departmentList = FXCollections.observableArrayList();

    private ObservableList<String> educationList = FXCollections.observableArrayList();


    private ObservableList<Worker> observableListWorker = FXCollections.observableArrayList();
    private ObservableList<Worker> observableListExperience = FXCollections.observableArrayList();


    private boolean exportDataToExcelWorker (String filePathTo){

        boolean flag = false;

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePathTo))))
        {
            writer.write("worker\n");

            for (int i = 0; i < observableListWorker.size(); i++) {
                String string = observableListWorker.get(i).getFIO() + "\n";
                writer.write(string);
            }
            flag = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private boolean exportDataToExcelExperience (String filePathTo){

        boolean flag = false;

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePathTo))))
        {
            writer.write("fio;work experience\n");

            for (int i = 0; i < observableListExperience.size(); i++) {
                String string = observableListExperience.get(i).getFIO() +
                         "; " + observableListExperience.get(i).getExperience()+ "\n";
                writer.write(string);
            }
            flag = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @FXML
    void excelExportExperience(MouseEvent event) {
        FileChooser excelFileChooser = new FileChooser();
        excelFileChooser.setTitle(TITLE_SAVE_AS);
        excelFileChooser.setInitialDirectory(new File(EXCEL_FILE_DIRECTORY));
        FileChooser.ExtensionFilter fnef = new FileChooser.ExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setSelectedExtensionFilter(fnef);
        File saveFile = excelFileChooser.showSaveDialog(null);
        if(saveFile != null){
            String filename = saveFile.getName();
            exportDataToExcelExperience(FILE_PATH_TO + filename + CSV_FORMAT);
        }
    }

    @FXML
    void excelExportWorkerList(MouseEvent event) {
        FileChooser excelFileChooser = new FileChooser();
        excelFileChooser.setTitle(TITLE_SAVE_AS);
        excelFileChooser.setInitialDirectory(new File(EXCEL_FILE_DIRECTORY));
        FileChooser.ExtensionFilter fnef = new FileChooser.ExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setSelectedExtensionFilter(fnef);
        File saveFile = excelFileChooser.showSaveDialog(null);
        if(saveFile != null){
            String filename = saveFile.getName();
            exportDataToExcelWorker(FILE_PATH_TO + filename + CSV_FORMAT);
        }
    }

    @FXML
    void showButtonOnAction(ActionEvent event) {

        if(departmentChoiceBox.getValue()!=null && educationChoiceBox.getValue()!=null){
            try {
                observableListWorker.clear();
                String department = departmentChoiceBox.getValue();
                String education = educationChoiceBox.getValue();
                CallableStatement cs = connection.prepareCall(PROCEDURE_SECOND);
                cs.setString(1, department);
                cs.setString(2, education);
                if (cs.execute()){
                    ResultSet rs = cs.getResultSet();
                    while(rs.next()){
                        observableListWorker.add(new Worker(rs.getString(1)));
                    }
                }

                workerTableColumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));

                workerTableView.setItems(observableListWorker);
            } catch (SQLException e) {
                Logger.getLogger(ClientUsefulInfoController.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = new DatabaseConnection().getConnection();

        try {
            ResultSet rs = connection.createStatement().executeQuery(VIEW_DEPARTMENT_LIST);
            while(rs.next()){
                departmentList.add(new Staff(rs.getString(1)).getDepartment());
            }
            ResultSet rs2 = connection.createStatement().executeQuery(VIEW_EDUCATION_LIST);
            while(rs2.next()){
                educationList.add(new Education(rs2.getString(1)).getEducation());
            }
            for (int i = 0; i < departmentList.size(); i++) {
                departmentChoiceBox.setItems(departmentList);
                educationChoiceBox.setItems(educationList);
            }

            ResultSet rs3 = connection.createStatement().executeQuery(QUERY_FOURTH);
            while(rs3.next()){
                observableListExperience.add(new Worker(rs3.getString(1), rs3.getInt(2)));
            }

            fioTableColumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));
            experienceTableColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));
            workExperienceTableView.setItems(observableListExperience);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
