package com.example.demo.Controller;

import com.example.demo.Main;
import com.example.demo.Model.Department;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;


public class EmployeeEditController {

    final DepartmentRepository departmentRepository = new DepartmentRepository();
    final EmployeeRepository employeeRepository = new EmployeeRepository();

    public Employee employee;


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @FXML
    public TextField Fname;
    @FXML
    public TextField Lname;
    @FXML
    public TextField Ssn;
    @FXML
    public DatePicker Bdate;
    @FXML
    public TextField Address;
    @FXML
    public ComboBox<String> Sex;
    @FXML
    public TextField Salary;
    @FXML
    public ComboBox<Department> Dnumber;

    @FXML
    private void initialize(){
        Platform.runLater(this::initializeFields);
    }

    public void initializeFields(){
        Fname.setText(employee.getFname());
        Lname.setText(employee.getLname());
        Ssn.setText(employee.getSsn());
        Ssn.setDisable(true);
        LocalDate date = new java.sql.Date(employee.getBdate().getTime()).toLocalDate();
        Bdate.setValue(date);
        Address.setText(employee.getAddress());
        Salary.setText(String.valueOf(employee.getSalary()));

        ObservableList<Department> dList = departmentRepository.getList();
        Dnumber.setItems(dList);

        String[] sex =
                { "M", "F" };

        Sex.setItems(FXCollections
                .observableArrayList(sex));

        Dnumber.setConverter(new StringConverter<Department>(){

            @Override
            public String toString(Department object) {
                return object == null ? null : object.getDname();
            }

            @Override
            public Department fromString(String string) {
                return Dnumber.getItems().stream().filter(i -> i.getDname().equals(string)).findAny().orElse(null);
            }

        });

        Dnumber.setCellFactory(new Callback<ListView<Department>, ListCell<Department>>(){

            @Override
            public ListCell<Department> call(ListView<Department> p) {

                final ListCell<Department> cell = new ListCell<Department>(){

                    @Override
                    protected void updateItem(Department t, boolean bln) {
                        super.updateItem(t, bln);

                        if(t != null){
                            setText(t.getDname() + ":  " + t.getDnumber());
                        }else{
                            setText(null);
                        }
                    }

                };

                return cell;
            }
        });

        Sex.setValue(employee.getSex());

        Department dept = departmentRepository.getByNumber(String.valueOf(employee.getDnumber()));
        Dnumber.setValue(dept);
    }

    public void updatePerson(ActionEvent actionEvent) {
        employeeRepository.updateEmployee(Fname.getText(),
                Lname.getText(),
                Ssn.getText(),
                String.valueOf(Bdate.getValue()),
                Address.getText(),
                Sex.getValue(),
                Salary.getText(),
                Dnumber.getValue().getDnumber());

        openEmployeePage(actionEvent);
    }

    public void openEmployeePage(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("employeeTable.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            stage.setTitle("База сотрудников");
            stage.setScene(scene);
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

