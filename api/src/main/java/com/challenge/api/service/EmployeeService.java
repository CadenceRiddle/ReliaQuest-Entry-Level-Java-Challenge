package com.challenge.api.service;

import com.challenge.api.model.Employee;//imports classes needed
import com.challenge.api.model.EmployeeImp;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.Instant;

@Service
public class EmployeeService {
    private final Map<UUID, EmployeeImp> mockDatabase = new HashMap<>(); //creates a map to store employees
    
    //Note: the below constructor needs to be commented if you want to run the tests

    public EmployeeService() {//constructor to add mock employees
        //Employee emp1 = new EmployeeImp(UUID.randomUUID(), "Alice", "Johnson", 90000, 28, "Software Engineer", "alice@example.com", Instant.now());
        //Employee emp2 = new EmployeeImp(UUID.randomUUID(), "John", "Doe", 80000, 30, "Software Engineer", "john.doe@example.com", Instant.now());

        //mockDatabase.put(emp1.getUuid(), emp1);
        //mockDatabase.put(emp2.getUuid(), emp2);
    }

    public List<EmployeeImp> getAllEmployees() {   //method to retrieve all employees
        return new ArrayList<>(mockDatabase.values());
    }

    public EmployeeImp getEmployeeByUuid(UUID uuid) { //method to retrieve an employee by UUID
        return mockDatabase.get(uuid);
    }

    public EmployeeImp createEmployee(EmployeeImp employee) { //method to create an employee
        mockDatabase.put(employee.getUuid(), employee);
        return employee;
    }
    
}
