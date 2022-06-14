package com.example.homeworkenv6.controller;

import com.example.homeworkenv6.Employee;
import com.example.homeworkenv6.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class FirstController {

    private final EmployeeService employeeService;

    public FirstController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/add")
    public Employee addEmployee(@RequestParam String name, @RequestParam String secondName) {
        return employeeService.addEmployee(name, secondName);
    }

    @GetMapping(value = "/delete")
    public Employee deleteEmployee(@RequestParam String name, @RequestParam String secondName) {
        return employeeService.deleteEmployee(name, secondName);
    }

    @GetMapping(value = "/find")
    public Employee findEmployee(@RequestParam String name, @RequestParam String secondName) {
        return employeeService.findEmployee(name, secondName);
    }

    @GetMapping(value = "/")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
