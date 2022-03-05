package com.example.demo.Controller;

import com.example.demo.Main;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.EmployeeRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;


public class EmployeeTableController {
    final EmployeeRepository employeeRepository = new EmployeeRepository();

    @FXML
    public TableView<Employee> employeeTable;
    @FXML
    public TableColumn<Employee, String> fNameColumn;
    @FXML
    public TableColumn<Employee, String> lNameColumn;
    @FXML
    public TableColumn<Employee, String> ssnColumn;
    @FXML
    public TableColumn<Employee, Date> bdateColumn;
    @FXML
    public TableColumn<Employee, String> addressColumn;
    @FXML
    public TableColumn<Employee, String> sexColumn;
    @FXML
    public TableColumn<Employee, Employee> editColumn;
    @FXML
    public TableColumn<Employee, Employee> deleteColumn;

    @FXML
    private void initialize(){
        fNameColumn.setCellValueFactory(cellData -> cellData.getValue().fnameProperty());
        lNameColumn.setCellValueFactory(cellData -> cellData.getValue().lnameProperty());
        ssnColumn.setCellValueFactory(cellData -> cellData.getValue().ssnProperty());
        bdateColumn.setCellValueFactory(cellData -> cellData.getValue().bdateProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        sexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        editColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        deleteColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));

        initializeTableValues();

        editColumn.setCellFactory(param -> new TableCell<Employee, Employee>() {
            private final Button editButton = new Button("Изменить");

            @Override
            protected void updateItem(Employee employee, boolean empty) {
                super.updateItem(employee, empty);

                if (employee == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);

                editButton.setOnAction(event -> editPerson(event, employee));
            }
        });

        deleteColumn.setCellFactory(param -> new TableCell<Employee, Employee>() {
            private final Button deleteButton = new Button("Удалить");

            @Override
            protected void updateItem(Employee employee, boolean empty) {
                super.updateItem(employee, empty);

                if (employee == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);

                deleteButton.setOnAction(event -> removePerson(employee));
            }

        });
    }

    public void removePerson(Employee employee) {
        employeeRepository.deleteEmployee(employee);
        initializeTableValues();
    }

    public void editPerson(ActionEvent event, Employee employee) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("employeeEdit.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            stage.setTitle("Редактирование сотрудника");
            stage.setScene(scene);
            EmployeeEditController controller = fxmlLoader.<EmployeeEditController>getController();
            controller.setEmployee(employee);
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeTableValues(){
        ObservableList<Employee> personList = employeeRepository.getList();
        if(personList.size() > 0){
            employeeTable.setItems(personList);
        }
    }

    public void openCreatePage(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("employeeCreate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            stage.setTitle("Добавление сотрудника");
            stage.setScene(scene);
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}