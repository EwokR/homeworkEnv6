package com.example.homeworkenv6.Service;

import com.example.homeworkenv6.Employee;
import com.example.homeworkenv6.exceptions.EmployeeAlreadyAddedException;
import com.example.homeworkenv6.exceptions.EmployeeNotFoundException;
import com.example.homeworkenv6.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    private String getKey(Employee employee) {
        return employee.getName() + "|" + employee.getSecondName();
    }

    @Override
    public Employee addEmployee(String name, String secondName, double salary, int division) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(name, secondName, salary,division);
        String key = getKey(employee);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            return employees.put(key, employee);
        }
        throw new EmployeeStorageIsFullException();
    }

    @Override
    public Employee deleteEmployee(String name, String secondName,double salary, int division) throws EmployeeNotFoundException {
        String key = getKey(new Employee(name, secondName, salary, division));
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    @Override
    public Employee findEmployee(String name, String secondName,double salary, int division) throws EmployeeNotFoundException {
        Employee employee = new Employee(name, secondName,salary, division);
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }
}

