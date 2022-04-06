package model.controller;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import staff.Staff;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.Constants.*;

public class ClientUsefulInfoController implements Initializable{

    @FXML
    private TableView<Staff> vacancyTableView;

    @FXML
    private TableColumn<Staff, String> positionTableColumn;

    @FXML
    private TableColumn<Staff, Integer> vacancyNumberTableColumn;

    @FXML
    private ImageView excelImageView;

    @FXML
    private TableView<Staff> salaryTableView;

    @FXML
    private TableColumn<Staff, String> salaryPositionTableColumn;

    @FXML
    private TableColumn<Staff, Double> salaryMinSalaryTableColumn;

    @FXML
    private ImageView salaryExcelImageView;

    @FXML
    private ChoiceBox<String> salaryDepartmentChoiceBox;

    @FXML
    private ChoiceBox<String> departmentChoiceBox;

    private Connection connection;

    private ObservableList<String> departmentList = FXCollections.observableArrayList();

    private ObservableList<Staff> observableList = FXCollections.observableArrayList();

    void showVacancyNumber() {
        vacancyTableView.setVisible(true);
        if(departmentChoiceBox.getValue()!=null){
            try {
                observableList.clear();
                String department = departmentChoiceBox.getValue();
                CallableStatement cs = connection.prepareCall(PROCEDURE_FIRST);
                cs.setString(1, department);
                if (cs.execute()){
                    ResultSet rs = cs.getResultSet();
                    while(rs.next()){
                        observableList.add(new Staff(rs.getString(1), Integer.parseInt(rs.getString(2))));
                    }
                }

                positionTableColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
                vacancyNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("vacancyNumber"));

                vacancyTableView.setItems(observableList);
            } catch (SQLException e) {

                Logger.getLogger(ClientUsefulInfoController.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }

    void showMinSalary(){
        salaryTableView.setVisible(true);
        if(salaryDepartmentChoiceBox.getValue()!=null){
            try {
                observableList.clear();
                String department = salaryDepartmentChoiceBox.getValue();
                CallableStatement cs = connection.prepareCall(PROCEDURE_MIN_SALARY);
                cs.setString(1, department);
                if (cs.execute()){
                    ResultSet rs = cs.getResultSet();
                    while(rs.next()){
                        observableList.add(new Staff(rs.getString(1), rs.getDouble(2)));
                    }
                }

                salaryPositionTableColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
                salaryMinSalaryTableColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

                salaryTableView.setItems(observableList);
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
            for (int i = 0; i < departmentList.size(); i++) {
                departmentChoiceBox.setItems(departmentList);
                salaryDepartmentChoiceBox.setItems(departmentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        departmentChoiceBox.setOnAction(event -> showVacancyNumber());
        salaryDepartmentChoiceBox.setOnAction(event -> showMinSalary());

    }

    @FXML
    void excelExport(MouseEvent event){

        FileChooser excelFileChooser = new FileChooser();
        excelFileChooser.setTitle(TITLE_SAVE_AS);
        excelFileChooser.setInitialDirectory(new File(EXCEL_FILE_DIRECTORY));
        FileChooser.ExtensionFilter fnef = new FileChooser.ExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setSelectedExtensionFilter(fnef);
        File saveFile = excelFileChooser.showSaveDialog(null);
        if(saveFile != null){
            String filename = saveFile.getName();
            exportDataToExcel(FILE_PATH_TO + filename + CSV_FORMAT);
        }

    }

    @FXML
    void salaryExcelExport(MouseEvent event){

        FileChooser excelFileChooser = new FileChooser();
        excelFileChooser.setTitle(TITLE_SAVE_AS);
        excelFileChooser.setInitialDirectory(new File(EXCEL_FILE_DIRECTORY));
        FileChooser.ExtensionFilter fnef = new FileChooser.ExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setSelectedExtensionFilter(fnef);
        File saveFile = excelFileChooser.showSaveDialog(null);
        if(saveFile != null){
            String filename = saveFile.getName();
            if(filename.matches(REGEX_FILENAME)){
                salaryExportDataToExcel(FILE_PATH_TO + filename);
            } else {
                salaryExportDataToExcel(FILE_PATH_TO + filename + CSV_FORMAT);
            }
        }

    }

    private boolean exportDataToExcel (String filePathTo){

        boolean flag = false;

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePathTo))))
        {
            writer.write("position;vacancy number\n");

            for (int i = 0; i < observableList.size(); i++) {
                String string = observableList.get(i).getPosition() + "; "
                        + observableList.get(i).getVacancyNumber() + "\n";
                writer.write(string);
            }
            flag = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private boolean salaryExportDataToExcel (String filePathTo){

        boolean flag = false;

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePathTo))))
        {
            writer.write("position;minimum salary\n");

            for (int i = 0; i < observableList.size(); i++) {
                String string = observableList.get(i).getPosition() + "; "
                        + observableList.get(i).getSalary() + "\n";
                writer.write(string);
            }
            flag = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}

