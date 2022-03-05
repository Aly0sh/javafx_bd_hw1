package com.example.demo.Repository;

import com.example.demo.Connection.ConnectionClass;
import com.example.demo.Model.Department;
import com.example.demo.Model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRepository {
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public ObservableList<Employee> getList(){
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            Statement statement=connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String sql="SELECT * FROM employee;";

            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                resultSet.previous();
                while (resultSet.next()) {
                    Employee employee = new Employee(
                            resultSet.getString("Fname"),
                            resultSet.getString("Lname"),
                            resultSet.getString("Ssn"),
                            resultSet.getDate("Bdate"),
                            resultSet.getString("Address"),
                            resultSet.getString("Sex"),
                            resultSet.getDouble("Salary"),
                            resultSet.getString("Super_ssn"),
                            resultSet.getString("Dnumber")
                    );
                    list.add(employee);
                }
            }else {
                System.out.println("no data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Employee getBySsn(String Ssn){
        Employee employee = null;
        try {
            Statement statement=connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String sql="SELECT * FROM employee WHERE Ssn = '"+Ssn+"' LIMIT 1;";

            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                resultSet.previous();
                while (resultSet.next()) {
                    Employee empl = new Employee(
                            resultSet.getString("Fname"),
                            resultSet.getString("Lname"),
                            resultSet.getString("Ssn"),
                            resultSet.getDate("Bdate"),
                            resultSet.getString("Address"),
                            resultSet.getString("Sex"),
                            resultSet.getDouble("Salary"),
                            resultSet.getString("Super_ssn"),
                            resultSet.getString("Dnumber")
                    );
                    employee = empl;
                }
            }else {
                System.out.println("no data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void deleteEmployee(Employee employee){
        try {
            Statement statement=connection.createStatement();
            String sql = "DELETE FROM employee WHERE ssn = '" + employee.getSsn() + "'";
            statement.executeUpdate(sql);

            System.out.println("Successfully deleted employee with ssn: " + employee.getSsn());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String Fname, String Lname, String Ssn, String Bdate, String Address, String Sex, String Salary, String Dnumber){
        try {
            Statement statement=connection.createStatement();
            String sql = "INSERT INTO " +
                    "employee(Fname, Lname, Ssn, Bdate, Address, Sex, Salary, Dnumber) " +
                    "VALUES ('"+Fname+"','" + Lname+"','" + Ssn+"','" + Bdate+"','" + Address+"','" + Sex+"','" + Salary+"', '"+ Dnumber+"')";
            statement.executeUpdate(sql);

            System.out.println("Successfully created!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(String Fname, String Lname, String Ssn, String Bdate, String Address, String Sex, String Salary, String Dnumber){
        try {
            Statement statement=connection.createStatement();
            String sql = "UPDATE employee SET  Fname = '"+Fname+"', Lname = '"+Lname+"', Bdate = '"+Bdate+"', Address = '"+Address+"', Sex = '"+Sex+"', Salary = '"+Salary+"', Dnumber = '"+Dnumber+"' WHERE Ssn = '"+Ssn+"';";
            statement.executeUpdate(sql);

            System.out.println("Successfully updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}