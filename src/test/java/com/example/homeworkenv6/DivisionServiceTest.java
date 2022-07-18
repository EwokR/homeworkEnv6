package com.example.homeworkenv6;

import com.example.homeworkenv6.Service.DivisionServiceimpl;
import com.example.homeworkenv6.Service.EmployeeServiceImpl;
import com.example.homeworkenv6.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collection;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class DivisionServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DivisionServiceimpl divisionServiceimpl;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Ivan", "Ivanov", 42_000, 1),
                new Employee("Peter", "Petrov", 28_000, 1),
                new Employee("Andrey", "Rudoy", 19_170, 2),
                new Employee("Koza", "Dereza", 60_000, 2),
                new Employee("Bochka", "Bass", 31_000, 2)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void employeeWithMaxSalaryPositiveTest(int division, Employee expected) {
        assertThat(divisionServiceimpl.findMaximumSalaryInDivision(division)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> divisionServiceimpl.findMaximumSalaryInDivision(3));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void employeeWithMinSalaryPositiveTest(int division, Employee expected) {
        assertThat(divisionServiceimpl.findMinimumSalaryInDivision(division)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> divisionServiceimpl.findMinimumSalaryInDivision(3));
    }

    @ParameterizedTest
    @MethodSource("printNamesInDivisionParams")
    public void printNamesInDivisionPositiveTest(int division, List<Employee> expected) {
       assertThat(divisionServiceimpl.printNamesInDivision(division)).containsExactlyElementsOf(expected);
    }

    @Test
    public void allEmployeeTest() {
        assertThat(divisionServiceimpl.allEmployee()).containsAllEntriesOf(
                Map.of(1, List.of(new Employee("Ivan", "Ivanov", 42_000, 1), new Employee("Peter", "Petrov", 28_000, 1)),
                        2,List.of(new Employee("Andrey", "Rudoy", 19_170, 2), new Employee("Koza", "Dereza", 60_000, 2), new Employee("Bochka", "Bass", 31_000, 2))
                )
        );
    }

    public static Stream<Arguments> employeeWithMaxSalaryParams() {
        return Stream.of(
                Arguments.of(1,new Employee("Sas","Sus",100_000,1)),
                Arguments.of(2, new Employee("Egor","Voronov",75_000,2))
        );
    }

    public static Stream<Arguments> employeeWithMinSalaryParams() {
        return Stream.of(
                Arguments.of(1,new Employee("Sas","Sus",100_000,1)),
                Arguments.of(2, new Employee("Egor","Voronov",75_000,2))
        );
    }

    public static Stream<Arguments> printNamesInDivisionParams() {
        return Stream.of(
                Arguments.of(1,List.of(new Employee("Ivan", "Ivanov", 42_000, 1), new Employee("Peter", "Petrov", 28_000, 1))),
                Arguments.of(2, List.of(new Employee("Andrey", "Rudoy", 19_170, 2), new Employee("Koza", "Dereza", 60_000, 2), new Employee("Bochka", "Bass", 31_000, 2))),
                Arguments.of(3, Collections.emptyList())
        );
    }
}
