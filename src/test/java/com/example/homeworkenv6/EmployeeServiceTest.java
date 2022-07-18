package com.example.homeworkenv6;

import com.example.homeworkenv6.Service.EmployeeService;
import com.example.homeworkenv6.Service.EmployeeServiceImpl;
import com.example.homeworkenv6.exceptions.EmployeeAlreadyAddedException;
import com.example.homeworkenv6.exceptions.EmployeeNotFoundException;
import com.example.homeworkenv6.exceptions.EmployeeStorageIsFullException;
import com.example.homeworkenv6.exceptions.IncorrectNameOrSecondNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.InstanceOfAssertFactories.STREAM;
import static org.assertj.core.api.AbstractIterableAssert.*;

public class EmployeeServiceTest {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("paramsForAdd")
    public void addNegativeTest1(String name,
                                 String secondName,
                                 double salary,
                                 int division) {
        Employee expected = new Employee(name,secondName,salary,division);
        assertThat(employeeService.addEmployee(name,secondName,salary,division)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()->employeeService.addEmployee(name,secondName,salary,division));
    }

    @ParameterizedTest
    @MethodSource("paramsForAdd")
    public void addNegativeTest2(String name,
                                 String secondName,
                                 double salary,
                                 int division) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(employeeService.addEmployee(employee.getName(),employee.getSecondName(),employee.getSalary(),employee.getDivision())).isEqualTo(employee)
        );

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(()-> employeeService.addEmployee(name,secondName,salary,division));
    }

    @ParameterizedTest
    @MethodSource("paramsForAddIncorrect")
    public void addNegativeTest3(String name,
                                 String secondName,
                                 double salary,
                                 int division) {
        Employee expected = new Employee(name,secondName,salary,division);
        assertThat(employeeService.addEmployee(name,secondName,salary,division)).isEqualTo(expected);

        assertThatExceptionOfType(IncorrectNameOrSecondNameException.class)
                .isThrownBy(()->employeeService.addEmployee(name,secondName,salary,division));
    }

    @ParameterizedTest
    @MethodSource("paramsForRemove")
    public void removeNegativeTest(String name,
                                   String secondName,
                                   double salary,
                                   int division) {
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()->employeeService.deleteEmployee("test", "test", 100_000,1));

        Employee expected =new Employee(name,secondName,salary,division);
        assertThat(employeeService.addEmployee(name,secondName,salary,division)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.deleteEmployee("test", "test", 100_000,1));
    }

    @ParameterizedTest
    @MethodSource("paramsForRemove")
    public void removePositiveTest(String name,
                                   String secondName,
                                   double salary,
                                   int division) {
        Employee expected =new Employee(name,secondName,salary,division);
        assertThat(employeeService.addEmployee(name,secondName,salary,division)).isEqualTo(expected);

        assertThat(employeeService.deleteEmployee(name,secondName,salary,division)).isEqualTo(expected);
        assertThat(employeeService.getAll().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("paramsForFind")
    public void findNegativeTest(String name,
                                   String secondName,
                                   double salary,
                                   int division) {
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()->employeeService.findEmployee("test", "test", 100_000,1));

        Employee expected =new Employee(name,secondName,salary,division);
        assertThat(employeeService.addEmployee(name,secondName,salary,division)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("test", "test", 100_000,1));
    }

    @ParameterizedTest
    @MethodSource("paramsForFind")
    public void findPositiveTest(String name,
                                   String secondName,
                                   double salary,
                                   int division) {
        Employee expected =new Employee(name,secondName,salary,division);
        assertThat(employeeService.addEmployee(name,secondName,salary,division)).isEqualTo(expected);

        assertThat(employeeService.findEmployee(name,secondName,salary,division)).isEqualTo(expected);
        assertThat(employeeService.getAll()).hasSize(1);
    }
    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1,i -> i + 1)
                .limit(size)
                .map(i -> new Employee("Name" + (char) ((int) 'a'+i),"Secondname" + (char) ((int) 'a' + i), i, 10_000 + i))
                        .collect(Collectors.toList());
    }

    public static Stream<Arguments> paramsForAdd() {
        return Stream.of(
                Arguments.of("Ivan","Ivanov",42_500,1),
                Arguments.of("Peter","Petrov",70_420,2),
                Arguments.of("Koza","Dereza",20_000,3)
        );
    }

    public static Stream<Arguments> paramsForRemove() {
        return Stream.of(
                Arguments.of("Ivan","Ivanov",42_500,1),
                Arguments.of("Peter","Petrov",70_420,2),
                Arguments.of("Koza","Dereza",20_000,3)
        );
    }

    public static Stream<Arguments> paramsForFind() {
        return Stream.of(
                Arguments.of("Ivan","Ivanov",42_500,1),
                Arguments.of("Peter","Petrov",70_420,2),
                Arguments.of("Koza","Dereza",20_000,3)
        );
    }

    public static Stream<Arguments> paramsForAddIncorrect() {
        return Stream.of(
                Arguments.of("Иван","Ivanov",42_500,1),
                Arguments.of("Peter","Петров",70_420,2),
                Arguments.of("Коза","Дереза",20_000,3)
        );
    }

}
