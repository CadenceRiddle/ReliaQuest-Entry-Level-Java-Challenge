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
    public EmployeeImp(UUID uuid, String firstName, String lastName, Integer salary, Integer age, String jobTitle, String email, Instant contractHireDate) {
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
    @Override public UUID getUuid() {
        return uuid;
    }
    @Override public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    @Override public String getFirstName() {
        return firstName;
    }
    @Override public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Override public String getLastName() {
        return lastName;
    }
    @Override public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override public String getFullName() {
        return firstName + " " + lastName;
    }
    @Override public void setFullName(String fullName) { 
        String[] names = fullName.split(" ", 2);
        if (names.length == 2) {
            this.firstName = names[0];
            this.lastName = names[1];
        }
    }
    @Override public Integer getSalary() {
        return salary;
    }
    @Override public void setSalary(Integer salary) {
        this.salary = salary;
    }
    @Override public Integer getAge() {
        return age;
    }
    @Override public void setAge(Integer age) {
        this.age = age;
    }
    @Override public String getJobTitle() {
        return jobTitle;
    }
    @Override public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    @Override public String getEmail() {
        return email;
    }
    @Override public void setEmail(String email) {
        this.email = email;
    }
    @Override public Instant getContractHireDate() {
        return contractHireDate;
    }
    @Override public void setContractHireDate(Instant contractHireDate) {
        this.contractHireDate = contractHireDate;
    }
    @Override public Instant getContractTerminationDate() {
        return contractTerminationDate;
    }
    @Override public void setContractTerminationDate(Instant contractTerminationDate) {
        this.contractTerminationDate = contractTerminationDate;
    }
}
