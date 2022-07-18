package com.example.homeworkenv6.controller;

import com.example.homeworkenv6.Employee;
import com.example.homeworkenv6.Service.DivisionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SecondController {

    private final DivisionService divisionService;

    public SecondController(DivisionService divisionService) {
        this.divisionService=divisionService;
    }

    @GetMapping(value = "/min-salary")
    public Employee minimumSalaryInDivision(@RequestParam int division) {
        return divisionService.findMinimumSalaryInDivision(division);
    }

    @GetMapping(value = "/max-salary")
    public Employee maximumSalaryInDivision(@RequestParam int division) {
        return divisionService.findMaximumSalaryInDivision(division);
    }

    @GetMapping(value = "/all")
    public List<Employee> printAllNamesInDivision(@RequestParam int division) {
        return divisionService.printNamesInDivision(division);
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> all() {
        return divisionService.allEmployee();
    }
}
