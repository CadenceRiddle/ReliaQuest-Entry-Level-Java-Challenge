package com.challenge.api.model;

import java.util.UUID; //imported universally unique identifier and instant timestamps
import java.time.Instant;



public class EmployeeImp implements Employee{
    
    private UUID uuid;//attributes for the employee
    private String firstName;
    private String lastName;
    private String fullName;
    private int salary;
    private int age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    //constructor for the employee
    public EmployeeImp(UUID uuid, String firstName, String lastName, int salary, int age, String jobTitle, String email, Instant contractHireDate) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = email;
        this.contractHireDate = contractHireDate;
    }

    //getters and setters for the employee
    @override public UUID getUuid() {
        return uuid;
    }
    @override public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    @override public String getFirstName() {
        return firstName;
    }
    @override public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @override public String getLastName() {
        return lastName;
    }
    @override public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @override public String getFullName() {
        return fullName;
    }
    @override public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @override public int getSalary() {
        return salary;
    }
    @override public void setSalary(int salary) {
        this.salary = salary;
    }
    @override public int getAge() {
        return age;
    }
    @override public void setAge(int age) {
        this.age = age;
    }
    @override public String getJobTitle() {
        return jobTitle;
    }
    @override public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    @override public String getEmail() {
        return email;
    }
    @override public void setEmail(String email) {
        this.email = email;
    }
    @override public Instant getContractHireDate() {
        return contractHireDate;
    }
    @override public void setContractHireDate(Instant contractHireDate) {
        this.contractHireDate = contractHireDate;
    }
    @override public Instant getContractTerminationDate() {
        return contractTerminationDate;
    }
    @override public void setContractTerminationDate(Instant contractTerminationDate) {
        this.contractTerminationDate = contractTerminationDate;
    }
}
