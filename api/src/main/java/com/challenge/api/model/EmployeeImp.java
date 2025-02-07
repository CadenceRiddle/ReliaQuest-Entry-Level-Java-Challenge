package com.challenge.api.model;

import java.util.UUID;
import java.time.Instant;



public class EmployeeImp implements Employee{
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private double salary;
    private int age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    
}
