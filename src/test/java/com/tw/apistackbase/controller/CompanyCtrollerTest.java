package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyCtrollerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompanyRepository companyRepository;

    @Test
    public void should_return_companies_when_get_companies()throws Exception{
        List<Company> mockCompanies= new ArrayList<Company>();
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(10002, "Test", 15, "male", 6000));
        mockCompanies.add(new Company("OOCL", employees, 1));
        Mockito.when(companyRepository.getCompanies()).thenReturn(mockCompanies);

        mockMvc.perform(get("/companies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"companyName\":\"OOCL\",\"employees\":[{\"id\":10002,\"name\":\"Test\",\"age\":15,\"gender\":\"male\"}],\"employeeNumber\":1}]"));

    }

    @Test
    public void should_return_companies_when_get_companies_by_id()throws Exception{
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(10001, "Test", 15, "male", 6000));
        Company mockCompany= new Company(1111,"OOCL", employees, 1);
        Mockito.when(companyRepository.getCompaniesById(1111)).thenReturn(mockCompany);

        mockMvc.perform(get("/companies/1111"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1111,\"companyName\":\"OOCL\",\"employees\":[{\"id\":10001,\"name\":\"Test\",\"age\":15,\"gender\":\"male\"}],\"employeeNumber\":1}"));

    }

    @Test
    public void should_return_employees_when_get_employees_by_id()throws Exception{
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(10001, "Test", 15, "male", 6000));
        Mockito.when(companyRepository.getEmployeesByCompanyId(1111)).thenReturn(employees);

        mockMvc.perform(get("/companies/1111/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1111,\"name\":\"Test\",\"age\":15,\"gender\":\"male\"}]"));

    }

    @Test
    public void should_return_companies_when_get_companies_by_page_and_page_size()throws Exception {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(10001, "Test", 15, "male", 6000));
        List<Company>list=new ArrayList<Company>(){{
            add(new Company(101,"OOCL", employees, 1));
            add(new Company(102,"TS", employees, 1));
        }};
        Mockito.when(companyRepository.getCompanies()).thenReturn(list);

        mockMvc.perform(get("/companies?page=1&pageSize=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":101,\"companyName\":\"OOCL\",\"employees\":[{\"id\":10001,\"name\":\"Test\",\"age\":15,\"gender\":\"male\"}],\"employeeNumber\":1}"));

    }


    @Test
    public void should_return_company_when_update_company()throws Exception{
        List<Company> companies= new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(10002, "Test", 15, "male", 6000));
        companies.add(new Company(1111, "OOCL", employees, 1));
        Mockito.when(companyRepository.getCompanies()).thenReturn(companies);

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"companyName\": \"OOTEST\",\n" +
                        "    \"employees\": [\n" +
                        "        {\n" +
                        "            \"id\": 10004,\n" +
                        "            \"name\": \"Test04\",\n" +
                        "            \"age\": 15,\n" +
                        "            \"gender\": \"male\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"employeeNumber\": 1\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"companyId\": 1112,\n" +
                        "    \"companyName\": \"OOTEST\",\n" +
                        "    \"employees\": [\n" +
                        "        {\n" +
                        "            \"id\": 10004,\n" +
                        "            \"name\": \"Test04\",\n" +
                        "            \"age\": 15,\n" +
                        "            \"gender\": \"male\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"employeeNumber\": 1\n" +
                        "}"));
    }

    @Test
    public void should_return_updated_company_when_request_update_company_api() throws Exception {
        List<Company> mockCompanies= new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(10002, "Test", 15, "male", 6000));
        mockCompanies.add(new Company(1111, "OOCL", employees, 1));
        Mockito.when(companyRepository.getCompanies()).thenReturn(mockCompanies);

        mockMvc.perform(put("/companies/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"id\": 1111,\n" +
                        "    \"companyName\": \"OOTESTUPDATE\",\n" +
                        "    \"employees\": [\n" +
                        "        {\n" +
                        "            \"id\": 1002,\n" +
                        "            \"name\": \"Test04\",\n" +
                        "            \"age\": 15,\n" +
                        "            \"gender\": \"male\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"employeeNumber\": 1\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1111,\n" +
                        "    \"companyName\": \"OOTESTUPDATE\",\n" +
                        "    \"employees\": [\n" +
                        "        {\n" +
                        "            \"id\": 1002,\n" +
                        "            \"name\": \"Test04\",\n" +
                        "            \"age\": 15,\n" +
                        "            \"gender\": \"male\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"employeeNumber\": 1\n" +
                        "}"));
    }

    @Test
    public void should_return_not_found_when_delete_companies_by_id()throws Exception{
        List<Company> mockCompanies= new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(10002, "Test", 15, "male", 6000));
        mockCompanies.add(new Company(1111, "OOCL", employees, 1));
        Mockito.when(companyRepository.getCompanies()).thenReturn(mockCompanies);

        mockMvc.perform(delete("/companies/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    }


