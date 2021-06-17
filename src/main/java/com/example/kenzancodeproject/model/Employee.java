package com.example.kenzancodeproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Employee {

    private final UUID id;
    private final String firstName;
    private final char middleInitial;
    private final String lastName;
    private final String dateOfBirth;
    private final String dateOfEmployment;
    private boolean active;

    public UUID getId() {
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public char getMiddleInitial(){
        return middleInitial;
    }

    public String getLastName(){
        return lastName;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public String getDateOfEmployment(){
        return dateOfEmployment;
    }

    public boolean getActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public Employee(@JsonProperty("id") UUID id,@JsonProperty("firstName") String firstName,@JsonProperty("middleInitial") char middleInitial,@JsonProperty("lastName") String lastName,@JsonProperty("dateOfBirth") String dateOfBirth,@JsonProperty("dateOfEmployment") String dateOfEmployment,@JsonProperty("active") boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        this.active = active;
    }
}
