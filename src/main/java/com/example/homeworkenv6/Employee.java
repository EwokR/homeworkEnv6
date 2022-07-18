package com.example.homeworkenv6;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee extends StringUtils {
    @JsonProperty("firstName")
    private String name;
    @JsonProperty("lastName")
    private String secondName;
    @JsonProperty("Salary")
    private double salary;
    @JsonProperty("division")
    private int division;

    public Employee(String name, String secondName, double salary, int division) {
        this.name = name;
        this.secondName = secondName;
        this.salary = salary;
        this.division = division;
    }

    public String getName() {
        return this.name;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public double getSalary() {
        return salary;
    }

    public int getDivision() {
        return division;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Name " + this.name + " Surname" + this.secondName + " Division" + this.division + " Salary" + this.salary;
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
        return Objects.hash(name, secondName, salary, division);
    }
}
