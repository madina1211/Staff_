package model.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import staff.Order;
import staff.Staff;
import staff.Worker;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.Constants.*;

public class ManagerTableController implements Initializable{
    @FXML
    private TableView<Worker> workerTableView;

    @FXML
    private TableColumn<Worker, Integer> wIDTableColumn;

    @FXML
    private TableColumn<Worker, String> wFioTableColumn;

    @FXML
    private TableColumn<Worker, String> wAddressTableColumn;

    @FXML
    private TableColumn<Worker, String> wPhoneTableColumn;

    @FXML
    private TableColumn<Worker, String> wBirthdateTableColumn;

    @FXML
    private TableColumn<Worker, Integer> wNationalityTableColumn;

    @FXML
    private TableColumn<Worker, String> wSexTableColumn;

    @FXML
    private TableColumn<Worker, Integer> wEducationIDTableColumn;

    @FXML
    private TableColumn<Worker, Integer> wDependentTableColumn;

    @FXML
    private TableColumn<Worker, String> wHiringDateTableColumn;

    @FXML
    private TableColumn<Worker, String> wFiringDateTableColumn;

    @FXML
    private TableColumn<Worker, Double> wSalaryTableColumn;

    @FXML
    private TableColumn<Worker, String> wUinTableColumn;

    @FXML
    private TableColumn<Worker, Integer> wPensionFundIDTableColumn;

    @FXML
    private TableColumn<Worker, Integer> wPositionIdTableColumn;

    @FXML
    private TableColumn<Worker, Integer> wDepartmentIDTableColumn;

    @FXML
    private JFXTextField wIdTextField;

    @FXML
    private JFXDatePicker wBirthdateTextField;

    @FXML
    private JFXTextField wFioTextField;

    @FXML
    private JFXTextField wAddressTextField;

    @FXML
    private JFXTextField wPhoneTextField;

    @FXML
    private JFXComboBox<String> wSexTextField;

    @FXML
    private JFXTextField wDependentTextField;

    @FXML
    private JFXTextField wEducationIDTextField;

    @FXML
    private JFXTextField wNationalityIDTextField;

    @FXML
    private JFXDatePicker wHiringDateTextField;

    @FXML
    private JFXDatePicker wFiringDateTextField;

    @FXML
    private JFXTextField wSalaryTextField;

    @FXML
    private JFXTextField wUinTextField;

    @FXML
    private JFXTextField wPensionFundIDTextField;

    @FXML
    private JFXTextField wPositionIDTextField;

    @FXML
    private JFXTextField wDepartmentIDTextField;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton editButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton clearButton;

    @FXML
    private Label messageLabel;

    private Connection connection;
    private ObservableList<Worker> observableList = FXCollections.observableArrayList();

    @FXML
    private Worker getWorkerFromTableView() {

        emptyTextField();

        Worker worker = workerTableView.getSelectionModel().getSelectedItem();

        wIdTextField.setText(String.valueOf(worker.getID()));
        wFioTextField.setText(worker.getFIO());
        wAddressTextField.setText(worker.getAddress());
        wBirthdateTextField.setValue(LocalDate.parse(worker.getBirthdate()));
        wPhoneTextField.setText(worker.getPhone());
        wNationalityIDTextField.setText(String.valueOf(worker.getNationalityID()));
        wSexTextField.setValue(worker.getSex());
        wEducationIDTextField.setText(String.valueOf(worker.getEducationID()));
        wDependentTextField.setText(String.valueOf(worker.getDependent()));
        if(worker.getFiringDate() != null ){
            wFiringDateTextField.setValue(LocalDate.parse(worker.getFiringDate()));
        }
        if(worker.getHiringDate() != null ){
            wHiringDateTextField.setValue(LocalDate.parse(worker.getHiringDate()));
        }
        if(worker.getSalary() != null){
            wSalaryTextField.setText(String.valueOf(worker.getSalary()));
        }
        wUinTextField.setText(worker.getUIN());
        wPensionFundIDTextField.setText(String.valueOf(worker.getPensionFundID()));
        if(worker.getPositionID() != null){
            wPositionIDTextField.setText(String.valueOf(worker.getPositionID()));
        }
        if(worker.getDepartmentID() != null){
            wDepartmentIDTextField.setText(String.valueOf(worker.getDepartmentID()));
        }


        return worker;
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {

        deleteTextInMessageLabel();

        String FIO = checkForNullStringAndReturnString(wFioTextField.getText());
        String address = checkForNullStringAndReturnString(wAddressTextField.getText());
        String phone = checkForNullStringAndReturnString(wPhoneTextField.getText());
        String birthdate = checkForNullStringAndReturnString(wBirthdateTextField.getValue().toString());
        Integer nationalityID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wNationalityIDTextField.getText()));
        String sex = checkForNullStringAndReturnString(wSexTextField.getValue());
        Integer educationID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wEducationIDTextField.getText()));
        Integer dependent = checkForNullIntegerAndReturnInteger(Integer.parseInt(wDependentTextField.getText()));
        String UIN = checkForNullStringAndReturnString(wUinTextField.getText());
        Integer pensionFundID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wPensionFundIDTextField.getText()));

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_WORKER);
            preparedStatement.setString(1, FIO);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, birthdate);
            preparedStatement.setInt(5, nationalityID);
            preparedStatement.setString(6, sex);
            preparedStatement.setInt(7, educationID);
            preparedStatement.setInt(8, dependent);
            preparedStatement.setString(9, UIN);
            preparedStatement.setInt(10, pensionFundID);

            preparedStatement.executeUpdate();
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText(SUCCESSFUL_ADDING_WORKER);

        } catch (SQLException e){
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(UNSUCCESSFUL_ADDING_WORKER);
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }

        updateTable();
        emptyTextField();

    }

    @FXML
    void clearButtonOnAction(ActionEvent event) {
        emptyTextField();
    }

    @FXML
    void deleteButtonOnAction(ActionEvent event) {

        deleteTextInMessageLabel();

        try {
            Worker deletedWorker = getWorkerFromTableView();

            String deleteQuery = DELETE_DATA_FROM_WORKER + deletedWorker.getID();

            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.execute();

            updateTable();
            emptyTextField();

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText(SUCCESSFUL_DELETE_WORKER);
        } catch (SQLException e) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(UNSUCCESSFUL_DELETE_WORKER);
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    @FXML
    void editButtonOnAction(ActionEvent event) {

        deleteTextInMessageLabel();


        String FIO = checkForNullStringAndReturnString(wFioTextField.getText());
        String address = checkForNullStringAndReturnString(wAddressTextField.getText());
        String phone = checkForNullStringAndReturnString(wPhoneTextField.getText());
        String birthdate = checkForNullStringAndReturnString(wBirthdateTextField.getValue().toString());
        Integer nationalityID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wNationalityIDTextField.getText()));
        String sex = checkForNullStringAndReturnString(wSexTextField.getValue());
        Integer educationID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wEducationIDTextField.getText()));
        Integer dependent = checkForNullIntegerAndReturnInteger(Integer.parseInt(wDependentTextField.getText()));
        Double salary = checkForNullDoubleAndReturnDouble(Double.parseDouble(wSalaryTextField.getText()));
        String UIN = checkForNullStringAndReturnString(wUinTextField.getText());
        Integer pensionFundID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wPensionFundIDTextField.getText()));
        Integer positionID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wPositionIDTextField.getText()));
        Integer departmentID = checkForNullIntegerAndReturnInteger(Integer.parseInt(wDepartmentIDTextField.getText()));

        try{
            Worker editedWorker = getWorkerFromTableView();

            String updateQuery = UPDATE_DATA_WORKER + editedWorker.getID();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, FIO);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, birthdate);
            preparedStatement.setInt(5, nationalityID);
            preparedStatement.setString(6, sex);
            preparedStatement.setInt(7, educationID);
            preparedStatement.setInt(8, dependent);
            preparedStatement.setDouble(9, salary);
            preparedStatement.setString(10, UIN);
            preparedStatement.setInt(11, pensionFundID);
            preparedStatement.setInt(12, positionID);
            preparedStatement.setInt(13, departmentID);

            preparedStatement.execute();

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText(SUCCESSFUL_UPDATE_WORKER);
        } catch (SQLException e){
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(UNSUCCESSFUL_UPDATE_WORKER);
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }

        updateTable();
        emptyTextField();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            connection = new DatabaseConnection().getConnection();
            wSexTextField.getItems().add("f");
            wSexTextField.getItems().add("m");
            orderTypeIDTextField.getItems().add(2);
            orderTypeIDTextField.getItems().add(3);
            orderTypeIDTextField.getItems().add(4);
            orderTypeIDTextField.getItems().add(5);
            orderTypeIDTextField.getItems().add(6);
            orderTypeIDTextField.getItems().add(7);
            orderTypeIDTextField.getItems().add(8);
            orderTypeIDTextField.getItems().add(9);
            orderTypeIDTextField.getItems().add(10);
            updateTable();
            updateTableOrder();
        } catch (Exception e){
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    private static String checkForNullStringAndReturnString(String string){
        if (string != null){
            return string;
        } else {
            return null;
        }
    }

    private static Integer checkForNullIntegerAndReturnInteger(Integer integer){
        if (integer != null){
            return integer;
        } else {
            return null;
        }
    }

    private static Double checkForNullDoubleAndReturnDouble (Double number){
        if (number != null){
            return number;
        } else {
            return null;
        }
    }

    @FXML
    private void updateTable (){

        observableList.clear();

        try {
            ResultSet rs = connection.createStatement().executeQuery(SELECT_ALL_FROM_WORKER);

            while (rs.next()){

                Integer ID = checkForNullIntegerAndReturnInteger(rs.getInt(1));
                String FIO = checkForNullStringAndReturnString(rs.getString(2));
                String address = checkForNullStringAndReturnString(rs.getString(3));
                String phone = checkForNullStringAndReturnString(rs.getString(4));
                String birthdate = checkForNullStringAndReturnString(rs.getString(5));
                Integer nationalityID = checkForNullIntegerAndReturnInteger(rs.getInt(6));
                String sex = checkForNullStringAndReturnString(rs.getString(7));
                Integer educationID = checkForNullIntegerAndReturnInteger(rs.getInt(8));
                Integer dependent = checkForNullIntegerAndReturnInteger(rs.getInt(9));
                String hiringDate = checkForNullStringAndReturnString(rs.getString(10));
                String firingDate = checkForNullStringAndReturnString(rs.getString(11));
                Double salary = checkForNullDoubleAndReturnDouble(rs.getDouble(12));
                String UIN = checkForNullStringAndReturnString(rs.getString(13));
                Integer pensionFundID = checkForNullIntegerAndReturnInteger(rs.getInt(14));
                Integer positionID = checkForNullIntegerAndReturnInteger(rs.getInt(15));
                Integer departmentID = checkForNullIntegerAndReturnInteger(rs.getInt(16));

                observableList.add(new Worker(ID,FIO,address,phone, birthdate, nationalityID,
                        sex, educationID, dependent, hiringDate, firingDate, salary, UIN, pensionFundID,
                        positionID, departmentID));
            }

        } catch (SQLException e){
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }

        wIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        wFioTableColumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        wAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        wPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        wBirthdateTableColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        wNationalityTableColumn.setCellValueFactory(new PropertyValueFactory<>("nationalityID"));
        wSexTableColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        wEducationIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("educationID"));
        wDependentTableColumn.setCellValueFactory(new PropertyValueFactory<>("dependent"));
        wHiringDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("hiringDate"));
        wFiringDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("firingDate"));
        wSalaryTableColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        wUinTableColumn.setCellValueFactory(new PropertyValueFactory<>("UIN"));
        wPensionFundIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("pensionFundID"));
        wPositionIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("positionID"));
        wDepartmentIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID"));

        workerTableView.setItems(observableList);
    }

    private void deleteTextInMessageLabel (){
        messageLabel.setText("");
    }

    private void emptyTextField (){

        wIdTextField.setText("");
        wFioTextField.setText("");
        wAddressTextField.setText("");
        wPhoneTextField.setText("");
        wBirthdateTextField.getEditor().clear();
        wNationalityIDTextField.setText("");
        wSexTextField.getEditor().clear();
        wEducationIDTextField.setText("");
        wDependentTextField.setText("");
        wHiringDateTextField.getEditor().clear();
        wFiringDateTextField.getEditor().clear();
        wSalaryTextField.setText("");
        wUinTextField.setText("");
        wPensionFundIDTextField.setText("");
        wPositionIDTextField.setText("");
        wDepartmentIDTextField.setText("");

    }

    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private TableColumn<Order, Integer> orderIDTableColumn;

    @FXML
    private TableColumn<Order, String> orderDateTableColumn;

    @FXML
    private TableColumn<Order, Integer> workerIDTableColumn;

    @FXML
    private TableColumn<Order, Integer> orderTypeIDTableColumn;

    @FXML
    private TableColumn<Order, String> contentTableColumn;

    @FXML
    private JFXTextField orderIDTextField;

    @FXML
    private JFXTextField workerIDTextField;

    @FXML
    private JFXTextField contentTextField;

    @FXML
    private JFXDatePicker orderDateTextField;

    @FXML
    private JFXComboBox<Integer> orderTypeIDTextField;

    @FXML
    private JFXButton addOrderButton;

    @FXML
    private Label messageLabel1;

    private ObservableList<Order> observableListOrder = FXCollections.observableArrayList();

    private void updateTableOrder (){
        observableListOrder.clear();

        try {

            ResultSet rs = connection.createStatement().executeQuery(SELECT_ALL_FROM_ORDER);

            while (rs.next()){

                Integer orderID = checkForNullIntegerAndReturnInteger(rs.getInt(1));
                String dateOrder = checkForNullStringAndReturnString(rs.getString(2));
                Integer workerID = checkForNullIntegerAndReturnInteger(rs.getInt(3));
                Integer orderTypeID = checkForNullIntegerAndReturnInteger(rs.getInt(4));
                String content = checkForNullStringAndReturnString(rs.getString(5));

                observableListOrder.add(new Order(orderID, dateOrder, workerID, orderTypeID, content));
            }

        } catch (SQLException e){
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }

        orderIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        orderDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        workerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        orderTypeIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderTypeID"));
        contentTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));

        orderTableView.setItems(observableListOrder);
    }

    @FXML
    private void addOrderButtonOnAction (){

        deleteTextInMessageLabel1();

        String orderDate = checkForNullStringAndReturnString(orderDateTextField.getValue().toString());
        Integer workerID = checkForNullIntegerAndReturnInteger(Integer.parseInt(workerIDTextField.getText()));
        Integer orderTypeID = checkForNullIntegerAndReturnInteger(orderTypeIDTextField.getValue());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDER);
            preparedStatement.setString(1, orderDate);
            preparedStatement.setInt(2, workerID);
            preparedStatement.setInt(3, orderTypeID);

            preparedStatement.executeUpdate();
            messageLabel1.setTextFill(Color.GREEN);
            messageLabel1.setText(SUCCESSFUL_ADDING_ORDER);

        } catch (SQLException e){
            messageLabel1.setTextFill(Color.RED);
            messageLabel1.setText(UNSUCCESSFUL_ADDING_ORDER);
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }

        updateTableOrder();
        emptyTextFieldOrder();

    }

    private void deleteTextInMessageLabel1 (){
        messageLabel1.setText("");
    }


    private void emptyTextFieldOrder (){

        orderIDTextField.setText("");
        orderDateTextField.getEditor().clear();
        workerIDTextField.setText("");
        orderTypeIDTextField.getEditor().clear();
        contentTextField.setText("");

    }


    @FXML
    private TableView<Staff> staffTableView;

    @FXML
    private TableColumn<Staff, String> departmentTableColumn;

    @FXML
    private TableColumn<Staff, String> positionTableColumn;

    @FXML
    private TableColumn<Staff, Integer> vacancyTableColumn;

    @FXML
    private TableColumn<Staff, Integer> totalTableColumn;

    private ObservableList<Staff> observableListStaff = FXCollections.observableArrayList();


    @FXML
    private void updateStaffingTable(){
        observableListStaff.clear();

        try {
            ResultSet rs = connection.createStatement().executeQuery(SELECT_ALL_FROM_STAFFING_TABLE);

            while (rs.next()){

                String department = checkForNullStringAndReturnString(rs.getString(1));
                String position = checkForNullStringAndReturnString(rs.getString(2));
                Integer vacancy = checkForNullIntegerAndReturnInteger(rs.getInt(3));
                Integer total = checkForNullIntegerAndReturnInteger(rs.getInt(4));

                observableListStaff.add(new Staff(department, position, vacancy, total));
            }

        } catch (SQLException e){
            Logger.getLogger(ManagerTableController.class.getName()).log(Level.SEVERE,null,e);
        }

        departmentTableColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        positionTableColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        vacancyTableColumn.setCellValueFactory(new PropertyValueFactory<>("vacancyNumber"));
        totalTableColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        staffTableView.setItems(observableListStaff);
    }




}

