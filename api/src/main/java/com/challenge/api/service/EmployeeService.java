package com.challenge.api.service;

import com.challenge.api.model.EmployeeImp;
import java.time.Instant;
import java.util.*;
import org.springframework.stereotype.Service;

@Service // service class to handle employee data
public class EmployeeService {
    private final Map<UUID, EmployeeImp> mockDatabase = new HashMap<>(); // creates a map to store employees

    public EmployeeService() { // constructor to add mock employees
        EmployeeImp emp1 =
                new EmployeeImp("Alice", "Johnson", 90000, 28, "Software Engineer", "alice@example.com", Instant.now());

        EmployeeImp emp2 =
                new EmployeeImp("John", "Doe", 80000, 30, "Software Engineer", "john.doe@example.com", Instant.now());

        EmployeeImp emp3 = new EmployeeImp(
                "Charlie", "Brown", 70000, 32, "QA Engineer", "charlie.brown@example.com", Instant.now());

        EmployeeImp emp4 = new EmployeeImp(
                "Emma", "Watson", 95000, 35, "Product Manager", "emma.watson@example.com", Instant.now());

        EmployeeImp emp5 = new EmployeeImp(
                "David", "Smith", 75000, 29, "DevOps Engineer", "david.smith@example.com", Instant.now());

        EmployeeImp emp6 = new EmployeeImp(
                "Sophia", "Miller", 85000, 27, "Data Scientist", "sophia.miller@example.com", Instant.now());

        EmployeeImp emp7 = new EmployeeImp(
                "Michael", "Brown", 92000, 40, "Engineering Manager", "michael.brown@example.com", Instant.now());

        EmployeeImp emp8 = new EmployeeImp(
                "Olivia", "Taylor", 78000, 26, "UI/UX Designer", "olivia.taylor@example.com", Instant.now());

        EmployeeImp emp9 = new EmployeeImp(
                "Ethan", "Wilson", 68000, 25, "Frontend Developer", "ethan.wilson@example.com", Instant.now());

        EmployeeImp emp10 = new EmployeeImp(
                "Lucas", "Anderson", 88000, 31, "Backend Developer", "lucas.anderson@example.com", Instant.now());

        mockDatabase.put(emp1.getUuid(), emp1);
        mockDatabase.put(emp2.getUuid(), emp2);
        mockDatabase.put(emp3.getUuid(), emp3);
        mockDatabase.put(emp4.getUuid(), emp4);
        mockDatabase.put(emp5.getUuid(), emp5);
        mockDatabase.put(emp6.getUuid(), emp6);
        mockDatabase.put(emp7.getUuid(), emp7);
        mockDatabase.put(emp8.getUuid(), emp8);
        mockDatabase.put(emp9.getUuid(), emp9);
        mockDatabase.put(emp10.getUuid(), emp10);
    }

    public List<EmployeeImp> getAllEmployees() { // method to retrieve all employees
        return new ArrayList<>(mockDatabase.values());
    }

    public EmployeeImp getEmployeeByUuid(UUID uuid) { // method to retrieve an employee by UUID
        return mockDatabase.get(uuid);
    }

    public EmployeeImp createEmployee(EmployeeImp employee) { // method to create an employee
        try {
            boolean emailExists =
                    mockDatabase
                            .values()
                            .stream() // checks for duplicate emails, this acts like the SQL UNIQUE constraint
                            .anyMatch(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail()));

            if (emailExists) {
                throw new IllegalArgumentException("Employee with email " + employee.getEmail() + " already exists!");
            }

            mockDatabase.put(employee.getUuid(), employee);

            if (!mockDatabase.containsKey(employee.getUuid())) {
                throw new RuntimeException(
                        "Failed to add employee: " + employee.getFirstName() + " " + employee.getLastName());
            }

            return employee;
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Log the error message
            throw e;
        }
    }
}
