package com.challenge.api;

import com.challenge.api.model.EmployeeImp; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeImpTest {

    private EmployeeImp employee;
    private UUID uuid;
    private Instant now;

    @BeforeEach
    void setUp() {
        now = Instant.now();
        
        employee = new EmployeeImp("John", "Doe", 80000, 30, "Software Engineer", "john.doe@example.com", Instant.now());
    }

    @Test
    void testGetters() {
        assertNotNull(employee.getUuid());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("John Doe", employee.getFullName());
        assertEquals("Software Engineer", employee.getJobTitle());
        assertEquals("john.doe@example.com", employee.getEmail());
        assertEquals(80000, employee.getSalary());
        assertEquals(30, employee.getAge());
        assertNotNull(employee.getContractHireDate());
        assertNull(employee.getContractTerminationDate());
    }

    @Test
    void testSetters() {
        UUID newUuid = UUID.randomUUID();
        employee.setUuid(newUuid);
        assertEquals(newUuid, employee.getUuid());

        employee.setFirstName("Jane");
        assertEquals("Jane", employee.getFirstName());

        employee.setLastName("Smith");
        assertEquals("Smith", employee.getLastName());

        employee.setSalary(90000);
        assertEquals(90000, employee.getSalary());

        employee.setAge(35);
        assertEquals(35, employee.getAge());

        employee.setJobTitle("Manager");
        assertEquals("Manager", employee.getJobTitle());

        employee.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", employee.getEmail());

        Instant terminationDate = Instant.now();
        employee.setContractTerminationDate(terminationDate);
        assertEquals(terminationDate, employee.getContractTerminationDate());
    }
}

