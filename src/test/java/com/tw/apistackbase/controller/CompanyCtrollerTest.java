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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyCtrollerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompanyRepository companyRepository;

    @Test
    public void should_return_conpanies_when_get_companies()throws Exception{
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
    }

