package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyRepository {
    private List<Company>companies=new ArrayList<>();

    public CompanyRepository() {
        this.companies = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(10001, "Test", 15, "male", 6000));
        companies.add(new Company(101,"OOCL", employees, employees.size()));
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public Company getCompaniesById(long id) {
        List<Company>list=getCompanies();
        return list.stream().filter(i->i.getId()==id).findFirst().orElse(null);
    }
}
