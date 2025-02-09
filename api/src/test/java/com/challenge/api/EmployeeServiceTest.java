package com.challenge.api;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImp;
import com.challenge.api.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.time.Instant;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeImp mockEmployee1;
    private EmployeeImp mockEmployee2;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(); // Initialize service

        // Create mock employees
        mockEmployee1 = new EmployeeImp("Alice", "Johnson", 90000, 28, "Software Engineer", "alice" + UUID.randomUUID() + "@example.com", Instant.now());
        mockEmployee2 = new EmployeeImp("John", "Doe", 80000, 30, "Software Engineer", "john.doe" + UUID.randomUUID() + "@example.com", Instant.now());

        // Add mock employees to the mock database
        employeeService.createEmployee(mockEmployee1);
        employeeService.createEmployee(mockEmployee2);
    }

    @Test
    void shouldReturnAllEmployees() {
        List<EmployeeImp> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertTrue(employees.size() >= 2, "Expected at least 2 employees, but found " + employees.size());

        List<String> firstNames = employees.stream()
                                        .map(EmployeeImp::getFirstName)
                                        .toList();

        assertTrue(firstNames.contains("Alice"), "Alice is missing from the employee list");
        assertTrue(firstNames.contains("John"), "John is missing from the employee list");
    }

    @Test
    void shouldReturnEmployeeByUuid() {
        EmployeeImp retrievedEmployee = employeeService.getEmployeeByUuid(mockEmployee1.getUuid());

        assertNotNull(retrievedEmployee);
        assertEquals(mockEmployee1.getUuid(), retrievedEmployee.getUuid());
        assertEquals("Alice", retrievedEmployee.getFirstName());
    }

    @Test
    void shouldReturnNullForNonexistentEmployee() {
        UUID randomUuid = UUID.randomUUID();
        EmployeeImp employee = employeeService.getEmployeeByUuid(randomUuid);

        assertNull(employee);
    }

    @Test
    void shouldCreateNewEmployee() {
        EmployeeImp newEmployee = new EmployeeImp("Charlie", "Brown", 300000, 24, "QA Engineer", "charlie.brown" + UUID.randomUUID() + "@example.com", Instant.now());

        EmployeeImp createdEmployee = employeeService.createEmployee(newEmployee);

        assertNotNull(createdEmployee);
        assertEquals("Charlie", createdEmployee.getFirstName());
        assertEquals("QA Engineer", createdEmployee.getJobTitle());
    }
}