package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity getEmployees(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "0") int pageSize, @RequestParam(value = "gender", defaultValue = "") String gender) {
        List<Employee> employees = employeeRepository.getEmployees();
        if (!gender.isEmpty()) {
            employees = employees.stream().filter(e -> e.getGender().equals(gender)).collect(Collectors.toList());
        }
        employees = (page == 0 || pageSize == 0 || pageSize > employees.size()) ? employees : employees.subList(page - 1, pageSize);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeesById(@PathVariable long id){
        return ResponseEntity.ok(employeeRepository.getEmployeeById(id));
    }

    @PostMapping("/employees")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        employeeRepository.addEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable(name = "id")long id,@RequestBody Employee employee){
        Employee oldEmployee=employeeRepository.getEmployeeById(id);
        employeeRepository.getEmployees().remove(oldEmployee);
        employeeRepository.getEmployees().add(employee);
        return ResponseEntity.ok(employee);
    }


}