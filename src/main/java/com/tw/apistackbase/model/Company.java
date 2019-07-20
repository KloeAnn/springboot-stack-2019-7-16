package com.tw.apistackbase.model;

import java.util.List;

public class Company {
    private long id;

    private String companyName;

    private List<Employee> employees;

    private int employeeNumber;

    public Company() {
    }

    public Company(String companyName, List<Employee> employees, int employeeNumber) {
        this.companyName = companyName;
        this.employees = employees;
        this.employeeNumber = employeeNumber;
    }

    public Company(long id,String companyName, List<Employee> employees, int employeeNumber) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
        this.employeeNumber = employeeNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
