package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyCtroller {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyRepository.getCompanies());
    }


}
