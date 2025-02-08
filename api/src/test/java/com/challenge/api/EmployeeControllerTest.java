package com.challenge.api;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImp;
import com.challenge.api.controller.EmployeeController;
import com.challenge.api.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeImp mockEmployee;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        // ✅ Use an actual Employee implementation
        mockEmployee = new EmployeeImp(UUID.randomUUID(), "John", "Doe", 60000, 30, "Software Engineer", "john.doe@example.com", Instant.now());
    }

    @Test
    void shouldReturnAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of((EmployeeImp) mockEmployee)); 

        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void shouldReturnEmployeeByUuid() throws Exception {
        when(employeeService.getEmployeeByUuid(mockEmployee.getUuid())).thenReturn(mockEmployee); 

        mockMvc.perform(get("/api/v1/employee/{uuid}", mockEmployee.getUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        EmployeeImp newEmployee = new EmployeeImp(UUID.randomUUID(), "John", "Doe", 60000, 30, "Software Engineer", "john.doe@example.com", Instant.now());

        when(employeeService.createEmployee(any(EmployeeImp.class))).thenReturn(newEmployee);

        String employeeJson = """
                {
                "firstName": "John",
                "lastName": "Doe",
                "email": "john.doe@example.com",
                "salary": 60000,
                "age": 30,
                "jobTitle": "Software Engineer"
                }
                """;

        mockMvc.perform(post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.jobTitle").value("Software Engineer"));
    }
}
