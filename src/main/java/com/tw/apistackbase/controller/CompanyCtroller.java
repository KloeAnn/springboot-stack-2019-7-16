package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyCtroller {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(@RequestParam(value="page",defaultValue ="0")int page,@RequestParam(value = "pageSize",defaultValue = "0") int pageSize){
        List<Company>list=companyRepository.getCompanies();
        return (page == 0 && pageSize == 0 || pageSize > list.size()) ? ResponseEntity.ok(companyRepository.getCompanies())
                : ResponseEntity.ok(list.subList(page - 1, pageSize));
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompaniesById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(companyRepository.getCompaniesById(id));
    }

    @GetMapping("/companies/{id}/employees")
    public ResponseEntity getEmployeesByCompanyId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(companyRepository.getEmployeesByCompanyId(id));
    }
}
