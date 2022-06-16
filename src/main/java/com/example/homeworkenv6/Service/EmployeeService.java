package com.example.homeworkenv6.Service;

import com.example.homeworkenv6.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(String name, String secondName,double salary, int division);
    public Employee deleteEmployee(String name, String secondName,double salary, int division);
    public Employee findEmployee(String name, String secondName,double salary, int division);
    public List<Employee> getAll();
}
