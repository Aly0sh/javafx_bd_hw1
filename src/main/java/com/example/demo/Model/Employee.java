package com.example.demo.Model;

import javafx.beans.property.*;

import java.util.Date;


public class Employee {
    private StringProperty Fname;
    private StringProperty Lname;
    private StringProperty Ssn;
    private ObjectProperty<Date> Bdate;
    private StringProperty Address;
    private StringProperty Sex;
    private DoubleProperty Salary;
    private StringProperty Super_ssn;
    private StringProperty Dnumber;

    public Employee(String fname,
                    String lname,
                    String ssn,
                    Date bdate,
                    String address,
                    String sex,
                    Double salary,
                    String super_ssn,
                    String dnumber) {
        Fname = new SimpleStringProperty(fname);
        Lname = new SimpleStringProperty(lname);
        Ssn = new SimpleStringProperty(ssn);
        Bdate = new SimpleObjectProperty<Date>(bdate);
        Address = new SimpleStringProperty(address);
        Sex = new SimpleStringProperty(sex);
        Salary = new SimpleDoubleProperty(salary);
        Super_ssn = new SimpleStringProperty(super_ssn);
        Dnumber = new SimpleStringProperty(dnumber);
    }

    public String getFname() {
        return Fname.get();
    }

    public StringProperty fnameProperty() {
        return Fname;
    }

    public void setFname(String fname) {
        this.Fname.set(fname);
    }

    public String getLname() {
        return Lname.get();
    }

    public StringProperty lnameProperty() {
        return Lname;
    }

    public void setLname(String lname) {
        this.Lname.set(lname);
    }

    public String getSsn() {
        return Ssn.get();
    }

    public StringProperty ssnProperty() {
        return Ssn;
    }

    public void setSsn(String ssn) {
        this.Ssn.set(ssn);
    }

    public Date getBdate() {
        return Bdate.get();
    }

    public ObjectProperty<Date> bdateProperty() {
        return Bdate;
    }

    public void setBdate(Date bdate) {
        this.Bdate.set(bdate);
    }

    public String getAddress() {
        return Address.get();
    }

    public StringProperty addressProperty() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address.set(address);
    }

    public String getSex() {
        return Sex.get();
    }

    public StringProperty sexProperty() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex.set(sex);
    }

    public double getSalary() {
        return Salary.get();
    }

    public DoubleProperty salaryProperty() {
        return Salary;
    }

    public void setSalary(double salary) {
        this.Salary.set(salary);
    }

    public String getSuper_ssn() {
        return Super_ssn.get();
    }

    public StringProperty super_ssnProperty() {
        return Super_ssn;
    }

    public void setSuper_ssn(String super_ssn) {
        this.Super_ssn.set(super_ssn);
    }

    public String getDnumber() {
        return Dnumber.get();
    }

    public StringProperty dnumberProperty() {
        return Dnumber;
    }

    public void setDnumber(String dnumber) {
        this.Dnumber.set(dnumber);
    }
}

