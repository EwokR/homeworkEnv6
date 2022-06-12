package com.example.homeworkenv4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee {
    @JsonProperty("firstName")
    private String name;
    @JsonProperty("lastName")
    private String secondName;


    public Employee(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    public String getName() {
        return this.name;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Name " + this.name + " Surname" + this.secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return name.equals(employee.name) && secondName.equals(employee.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName);
    }
}
