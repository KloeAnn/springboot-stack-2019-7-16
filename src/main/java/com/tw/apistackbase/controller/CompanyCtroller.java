package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/companies")
    public ResponseEntity addCompanies(@RequestBody Company company){
        companyRepository.addCompanies(company);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity updateCompany(@PathVariable(name = "id")long id,@RequestBody Company company){
        Company oldCompany=companyRepository.getCompaniesById(id);
        companyRepository.getCompanies().remove(oldCompany);
        companyRepository.getCompanies().add(company);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity deleteCompany(@PathVariable(name = "id")long id){
       Company company=companyRepository.getCompaniesById(id);
       companyRepository.getCompanies().remove(company);
       Company resCompany=companyRepository.getCompanies().stream().filter(i->i.getId()==id).findFirst().orElse(null);
       return ResponseEntity.notFound().build();
    }
}
