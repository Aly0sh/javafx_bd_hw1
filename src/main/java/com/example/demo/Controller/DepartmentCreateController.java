package com.example.demo.Controller;

import com.example.demo.Main;
import com.example.demo.Model.Department;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
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

public class DepartmentCreateController {
    final DepartmentRepository departmentRepository = new DepartmentRepository();
    final EmployeeRepository employeeRepository = new EmployeeRepository();

    @FXML
    public TextField Dname;
    @FXML
    public TextField Dnumber;
    @FXML
    public ComboBox<Employee> Mgr_ssn;
    @FXML
    public DatePicker Mgr_start_date;

    @FXML
    private void initialize(){
        initializeFields();
    }

    public void initializeFields(){
        ObservableList<Employee> employeeList = employeeRepository.getList();
        Mgr_ssn.setItems(employeeList);

        Mgr_ssn.setConverter(new StringConverter<Employee>(){

            @Override
            public String toString(Employee object) {
                return object == null ? null : object.getSsn();
            }

            @Override
            public Employee fromString(String string) {
                return Mgr_ssn.getItems().stream().filter(i -> i.getSsn().equals(string)).findAny().orElse(null);
            }

        });

        Mgr_ssn.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>(){

            @Override
            public ListCell<Employee> call(ListView<Employee> employeeListView) {
                final ListCell<Employee> cell = new ListCell<Employee>(){
                    @Override
                    protected void updateItem(Employee employee, boolean b) {
                        super.updateItem(employee, b);

                        if(employee != null){
                            setText(employee.getFname() + " " + employee.getFname() + ": " + employee.getSsn());
                        }else{
                            setText(null);
                        }
                    }
                };

                return cell;
            }
        });
    }

    public void insertDepartment(ActionEvent actionEvent) {
        departmentRepository.addDepartment(
                Dname.getText(),
                Dnumber.getText(),
                Mgr_ssn.getValue().getSsn(),
                String.valueOf(Mgr_start_date.getValue())
        );

        openDepartmentPage(actionEvent);
    }

    public void openDepartmentPage(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("departmentTable.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            stage.setTitle("Department base");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
