package com.example.homeworkenv6.Service;

import com.example.homeworkenv6.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(String name, String secondName);
    public Employee deleteEmployee(String name, String secondName);
    public Employee findEmployee(String name, String secondName);
    public List<Employee> getAll();
}
