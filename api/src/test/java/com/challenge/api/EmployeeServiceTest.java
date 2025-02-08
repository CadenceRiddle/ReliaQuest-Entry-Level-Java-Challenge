package com.challenge.api;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    private Employee mockEmployee1;
    private Employee mockEmployee2;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(); // Initialize service

        // Create mock employees
        mockEmployee1 = new EmployeeImpl(UUID.randomUUID(), "Alice", "Johnson", "Software Engineer", "alice@example.com", 90000, 28);
        mockEmployee2 = new EmployeeImpl(UUID.randomUUID(), "Bob", "Smith", "Product Manager", "bob@example.com", 120000, 35);

        // Add mock employees to the mock database
        employeeService.createEmployee(mockEmployee1);
        employeeService.createEmployee(mockEmployee2);
    }

    @Test
    void shouldReturnAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertEquals(4, employees.size()); // 2 predefined + 2 from setup
        assertEquals("Alice", employees.get(2).getFirstName());
        assertEquals("Bob", employees.get(3).getFirstName());
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
        Employee newEmployee = new EmployeeImpl(UUID.randomUUID(), "Charlie", "Brown", "QA Engineer", "charlie@example.com", 70000, 32);

        Employee createdEmployee = employeeService.createEmployee(newEmployee);

        assertNotNull(createdEmployee);
        assertEquals("Charlie", createdEmployee.getFirstName());
        assertEquals("QA Engineer", createdEmployee.getJobTitle());
    }
}
