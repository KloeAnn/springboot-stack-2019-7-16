package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class EmployeeRepository {
    List<Employee>employees;

    public EmployeeRepository(){
        this.employees=new ArrayList<>();

          employees.add(new Employee(10001, "01-name", 15, "male", 6000));
          employees.add(new Employee(10002, "02-name", 20, "male", 7000));
          employees.add(new Employee(10003, "03-name", 18, "female", 6500));
    }


    public Employee getEmployeeById(long id){
        List<Employee>employees=getEmployees();
        Employee employee=employees.stream().filter(i->i.getAge()==id).findFirst().orElse(null);
        return  employee;
    }

    public List<Employee> getEmployeeByGender(String gender){
        List<Employee>employees=getEmployees();
        List<Employee> list=employees.stream().filter(i->i.getGender()==gender).collect(Collectors.toList());
        return  list;
    }

    public void addEmployee(Employee employee) {
         getEmployees().add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
