package com.challenge.api.service;

import com.challenge.api.model.Employee;//imports classes needed
import com.challenge.api.model.EmployeeImpl;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeService {
    private final Map<UUID, Employee> mockDatabase = new HashMap<>(); //creates a map to store employees

    public EmployeeService() {//constructor to add mock employees
        Employee emp1 = new EmployeeImpl(UUID.randomUUID(), "Alice", "Johnson", "Software Engineer", "alice@example.com", 90000, 28);
        Employee emp2 = new EmployeeImpl(UUID.randomUUID(), "Bob", "Smith", "Product Manager", "bob@example.com", 120000, 35);
        mockDatabase.put(emp1.getUuid(), emp1);
        mockDatabase.put(emp2.getUuid(), emp2);
    }

    public List<Employee> getAllEmployees() {   //method to retrieve all employees
        return new ArrayList<>(mockDatabase.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) { //method to retrieve an employee by UUID
        return mockDatabase.get(uuid);
    }

    public Employee createEmployee(Employee employee) { //method to create a new employee
        mockDatabase.put(employee.getUuid(), employee);
        return employee;
    }
}
