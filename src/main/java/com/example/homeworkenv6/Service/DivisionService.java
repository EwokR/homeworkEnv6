package com.example.homeworkenv6.Service;

import com.example.homeworkenv6.Employee;

import java.util.List;
import java.util.Map;

public interface DivisionService {
    public Employee findMinimumSalaryInDivision(int division);
    public Employee findMaximumSalaryInDivision(int division);
    public List<Employee> printNamesInDivision(int division);
    public Map<Integer, List<Employee>> allEmployee();
}
