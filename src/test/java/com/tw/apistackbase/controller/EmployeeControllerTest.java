package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_employees_when_get_employees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "01-name", 15, "male", 6000));

        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));
    }

    @Test
    public void should_return_employee_when_get_employee_with_id() throws Exception {

        Employee employee = new Employee(1, "01-name", 15, "male", 6000);

        Mockito.when(employeeRepository.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void should_return_employee__when_get_employee_with_page_and_page_size() throws Exception {
        List<Employee> mockEmployeeList = new ArrayList<>();
        mockEmployeeList.add(new Employee(10001, "Test", 15, "male", 6000));
        mockEmployeeList.add(new Employee(10002, "Test2", 17, "female", 7000));
        mockEmployeeList.add(new Employee(10003, "Test3", 19, "male", 8000));
        Mockito.when(employeeRepository.getEmployees()).thenReturn(mockEmployeeList);

        mockMvc.perform(get("/employees?page=2&pageSize=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(10002))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(10003));
    }
}