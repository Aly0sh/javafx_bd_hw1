package com.example.demo.Controller;

import com.example.demo.Connection.ConnectionClass;
import com.example.demo.Model.Department;
import com.example.demo.Model.Employee;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DepartmentController {
//    @FXML
//    public TableView<Department> departmentTable;
//    @FXML
//    public TableColumn<Department, String> dNameColumn;
//    @FXML
//    public TableColumn<Department, String> mgrSsnColumn;
//    @FXML
//    public TableColumn<Department, Date> mgrStartDateColumn;
//    @FXML
//    public TableColumn<Department, Department> actionColumn;
//
//    @FXML
//    public TextField Dname;
//    @FXML
//    public TextField Dnumber;
//    @FXML
//    public TextField Mgr_ssn;
//    @FXML
//    public DatePicker Mgr_start_date;
//
//    @FXML
//    public void initialize(){
//        dNameColumn.setCellValueFactory(cellData -> cellData.getValue().dnameProperty());
//        mgrSsnColumn.setCellValueFactory(cellData -> cellData.getValue().mgr_ssnProperty());
//        mgrStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().mgr_start_dateProperty());
//        actionColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
//
//        initializeTableValues();
//
//        actionColumn.setCellFactory(param -> new TableCell<Department, Department>() {
//            private final Button deleteButton = new Button("Delete");
//
//            @Override
//            protected void updateItem(Department department, boolean empty) {
//                super.updateItem(department, empty);
//
//                if (department == null) {
//                    setGraphic(null);
//                    return;
//                }
//
//                setGraphic(deleteButton);
//
//                deleteButton.setOnAction(event -> removeDepartment(department.getDnumber()));
//            }
//        });
//    }
//
//    public void removeDepartment(String dNumber) {
//        try {
//            Statement statement=connection.createStatement();
//            String sql = "DELETE FROM department WHERE Dnumber = '" + dNumber + "'";
//            statement.executeUpdate(sql);
//
//            System.out.println("Success!");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        initializeTableValues();
//    }
//
//    ConnectionClass connectionClass = new ConnectionClass();
//    Connection connection = connectionClass.getConnection();
//
//    public void initializeTableValues(){
//        try {
//            Statement statement=connection.createStatement();
//
//            String sql="SELECT * FROM department;";
//
//            ResultSet resultSet=statement.executeQuery(sql);
//
//            ObservableList<Department> departmentsList = FXCollections.observableArrayList();
//
//            if (resultSet.next()){
//                while (resultSet.next()) {
//                    Department department = new Department(
//                            resultSet.getString("Dname"),
//                            resultSet.getInt("Dnumber"),
//                            resultSet.getString("Mgr_ssn"),
//                            resultSet.getDate("Mgr_start_date")
//                    );
//                    departmentsList.add(department);
//                }
//                departmentTable.setItems(departmentsList);
//            }else {
//                System.out.println("no data");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void insertDepartment(ActionEvent actionEvent) {
//        try {
//            Statement statement=connection.createStatement();
//            String sql = "INSERT INTO " +
//                    "department(Dname, Dnumber, Mgr_ssn, Mgr_start_date) " +
//                    "VALUES ('"+Dname.getText()+"','" +
//                    ""+Dnumber.getText()+"','" +
//                    ""+Mgr_ssn.getText()+"','" +
//                    ""+Mgr_start_date.getValue()+"')";
//            statement.executeUpdate(sql);
//
//            System.out.println("Success!");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Dname.setText("");
//        Dnumber.setText("");
//        Mgr_ssn.setText("");
//
//        initializeTableValues();
//    }

}
