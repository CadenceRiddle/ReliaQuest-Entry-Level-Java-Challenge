package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @InjectMocks
    private EmployeeController employeeController;

    private Employee mockEmployee;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        // Mock Employee Implementation
        mockEmployee = new Employee() {
            private UUID uuid = UUID.randomUUID();
            private String firstName = "John";
            private String lastName = "Doe";
            private String email = "john.doe@example.com";
            private Integer salary = 60000;
            private Integer age = 30;
            private String jobTitle = "Software Engineer";
            private Instant hireDate = Instant.now();

            @Override public UUID getUuid() { return uuid; }
            @Override public void setUuid(UUID uuid) { this.uuid = uuid; }
            @Override public String getFirstName() { return firstName; }
            @Override public void setFirstName(String name) { this.firstName = name; }
            @Override public String getLastName() { return lastName; }
            @Override public void setLastName(String name) { this.lastName = name; }
            @Override public String getFullName() { return firstName + " " + lastName; }
            @Override public void setFullName(String name) {}
            @Override public Integer getSalary() { return salary; }
            @Override public void setSalary(Integer salary) { this.salary = salary; }
            @Override public Integer getAge() { return age; }
            @Override public void setAge(Integer age) { this.age = age; }
            @Override public String getJobTitle() { return jobTitle; }
            @Override public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
            @Override public String getEmail() { return email; }
            @Override public void setEmail(String email) { this.email = email; }
            @Override public Instant getContractHireDate() { return hireDate; }
            @Override public void setContractHireDate(Instant date) { this.hireDate = date; }
            @Override public Instant getContractTerminationDate() { return null; }
            @Override public void setContractTerminationDate(Instant date) {}
        };
    }

    @Test
    void shouldReturnAllEmployees() throws Exception {
        when(employeeController.getAllEmployees()).thenReturn(List.of(mockEmployee));

        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void shouldReturnEmployeeByUuid() throws Exception {
        when(employeeController.getEmployeeByUuid(mockEmployee.getUuid())).thenReturn(mockEmployee);

        mockMvc.perform(get("/api/v1/employee/{uuid}", mockEmployee.getUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        when(employeeController.createEmployee(mockEmployee)).thenReturn(mockEmployee);

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
