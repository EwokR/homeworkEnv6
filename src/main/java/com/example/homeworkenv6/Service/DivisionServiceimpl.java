package com.example.homeworkenv6.Service;

import com.example.homeworkenv6.Employee;
import com.example.homeworkenv6.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DivisionServiceimpl implements DivisionService {

    private final EmployeeServiceImpl employeeService;

    public DivisionServiceimpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findMinimumSalaryInDivision(int division) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDivision() == division)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(()-> new EmployeeNotFoundException());
    }

    @Override
    public Employee findMaximumSalaryInDivision(int division) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDivision() == division)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(()-> new EmployeeNotFoundException());
    }

    @Override
    public List<Employee> printNamesInDivision(int division) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDivision() == division)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> allEmployee() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(e -> e.getDivision(),Collectors.toList()));
    }
}

