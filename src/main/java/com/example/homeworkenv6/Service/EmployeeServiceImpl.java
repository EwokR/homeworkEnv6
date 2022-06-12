package com.example.homeworkenv4.Service;

import com.example.homeworkenv4.Employee;
import com.example.homeworkenv4.exceptions.EmployeeAlreadyAddedException;
import com.example.homeworkenv4.exceptions.EmployeeNotFoundException;
import com.example.homeworkenv4.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employees = List.of(
            new Employee("Sas", "Sus"),
            new Employee("Ivan", "Ivanov"),
            new Employee("Petr", "Petrov"),
            new Employee("Andrei", "Sidorov"),
            new Employee("Mihail", "Hrustalyov"),
            new Employee("Denis", "Krylov"),
            new Employee("Stepan", "Nedotepkin"),
            new Employee("Mudrets", "Vseznamus"),
            new Employee("Chel", "Chelovekov"),
            new Employee("Anton", "Chekhov")
            );

    @Override
    public Employee addEmployee(String name, String secondName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(name,secondName);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(null) && employees.get(i).getSecondName().equals(null)) {
                employees.get(i).setName(name);
                employees.get(i).setSecondName(secondName);
                System.out.println("Employee " + name + " " + secondName + " is added.");
            } else if (employees.get(i).getName().equals(name) && employees.get(i).getSecondName().equals(secondName)) {
                throw new EmployeeAlreadyAddedException("Employee is already added.");
            } else {
                throw new EmployeeStorageIsFullException("Employee storage is full.");
            }
        }
        return employee;
    }

    @Override
    public Employee deleteEmployee(String name, String secondName) throws EmployeeNotFoundException {
        Employee employee = new Employee(name,secondName);
        for (int i = 0; i < employees.size(); i++) {
            if (name.equals(employees.get(i).getName()) && secondName.equals(employees.get(i).getSecondName())) {
                employees.remove(i);
                System.out.println("Employee " + name + " " + secondName + " is deleted.");
            } else {
                throw new EmployeeNotFoundException("Employee not found");
            }
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String name, String secondName) throws EmployeeNotFoundException {
        Employee employee = new Employee(name,secondName);
        for (int i = 0; i < employees.size(); i++) {
            if (name.equals(employees.get(i).getName()) && secondName.equals(employees.get(i).getSecondName())) {
                System.out.println("Employee" + name + " " + secondName + " found");
            } else {
                throw new EmployeeNotFoundException("Employee not found");
            }
        }
        return employee;
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }

}

