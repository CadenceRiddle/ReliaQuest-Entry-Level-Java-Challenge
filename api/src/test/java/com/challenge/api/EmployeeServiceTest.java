package com.challenge.api;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImp;
import com.challenge.api.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    private Employee mockEmployee1;
    private Employee mockEmployee2;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(); // Initialize service

        // Create mock employees
        mockEmployee1 = new EmployeeImp(UUID.randomUUID(), "Alice", "Johnson", 90000, 28, "Software Engineer", "alice@example.com", Instant.now());
        mockEmployee2 = new EmployeeImp(UUID.randomUUID(), "John", "Doe", 80000, 30, "Software Engineer", "john.doe@example.com", Instant.now());

        // Add mock employees to the mock database
        employeeService.createEmployee(mockEmployee1);
        employeeService.createEmployee(mockEmployee2);
    }

    @Test
    void shouldReturnAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertTrue(employees.size() >= 2); // Should at least contain the two mock employees
        assertEquals("Alice", employees.get(employees.size() - 2).getFirstName());
        assertEquals("John", employees.get(employees.size() - 1).getFirstName());
    }

    @Test
    void shouldReturnEmployeeByUuid() {
        Employee retrievedEmployee = employeeService.getEmployeeByUuid(mockEmployee1.getUuid());

        assertNotNull(retrievedEmployee);
        assertEquals(mockEmployee1.getUuid(), retrievedEmployee.getUuid());
        assertEquals("Alice", retrievedEmployee.getFirstName());
    }

    @Test
    void shouldReturnNullForNonexistentEmployee() {
        UUID randomUuid = UUID.randomUUID();
        Employee employee = employeeService.getEmployeeByUuid(randomUuid);

        assertNull(employee);
    }

    @Test
    void shouldCreateNewEmployee() {
        Employee newEmployee = new EmployeeImp(UUID.randomUUID(), "Charlie", "Brown", 300000, 24, "QA Engineer", "charlie@example.com", Instant.now());

        Employee createdEmployee = employeeService.createEmployee(newEmployee);

        assertNotNull(createdEmployee);
        assertEquals("Charlie", createdEmployee.getFirstName());
        assertEquals("QA Engineer", createdEmployee.getJobTitle());
    }
}