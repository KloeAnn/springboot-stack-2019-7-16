package com.tw.apistackbase.controller;

import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity getEmployees(){
        return ResponseEntity.ok(employeeRepository.getEmployees());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployees(@PathVariable long id){
        return ResponseEntity.ok(employeeRepository.getEmployeeById(id));
    }
}