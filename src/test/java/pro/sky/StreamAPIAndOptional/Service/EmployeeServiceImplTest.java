package pro.sky.StreamAPIAndOptional.Service;

import org.assertj.core.internal.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.StreamAPIAndOptional.Employee;
import pro.sky.StreamAPIAndOptional.Service.UnitTest.GenerateTest;
import pro.sky.StreamAPIAndOptional.Service.UnitTest.GenerateTest.*;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeAlreadyAddedException;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeNotFoundException;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;
import static org.assertj.core.internal.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.StreamAPIAndOptional.Service.UnitTest.GenerateTest.*;

class EmployeeServiceImplTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    void put_susses() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY_1;

        //подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee1();
        //начало теста
        Employee actualEmployee = employeeService.put(firstName, lastName, departmentId, salaryEmployee);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void put_sussesEmployeeAlreadyAddedException() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY_1;

        //подготовка ожидаемого результата
        String exceptionMessage = "400 данный сотрудник уже существует";
        //начало теста
        employeeService.put(firstName, lastName, departmentId, salaryEmployee);
        Exception exception = assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeService.put(firstName, lastName, departmentId, salaryEmployee)
        );
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void put_sussesEmployeeStorageIsFullException() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        double salaryEmployee = SALARY_1;
        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salaryEmployee2 = SALARY_2;
        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salaryEmployee3 = SALARY_3;

        Integer departmentId = FIRST_DEPARTMENT_ID;
        //подготовка ожидаемого результата
        String exceptionMessage = "400 Нет места для добавления сотрудника";

        //начало теста
        employeeService.put(firstName, lastName, departmentId, salaryEmployee);
        employeeService.put(firstName2, lastName2, departmentId, salaryEmployee2);
        employeeService.put(firstName3, lastName3, departmentId, salaryEmployee3);
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.put(firstName, lastName2, departmentId, salaryEmployee3)
        );
        assertEquals(exceptionMessage, exception.getMessage());
    }


    @Test
    void remove_susses() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY_1;

        //подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee1();
        //начало теста
        employeeService.put(firstName, lastName, departmentId, salaryEmployee);
        Employee actualEmployee = employeeService.remove(firstName, lastName, departmentId, salaryEmployee);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void remove_EmployeeNotFoundException() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY_1;
        //подготовка ожидаемого результата
        String exceptionMessage = "404 невозможно удалить,данный сотрудник не найден";
        //начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.remove(firstName, lastName, departmentId, salaryEmployee)
        );
        assertEquals(exceptionMessage, exception.getMessage());

    }

    @Test
    void find_susses() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY_1;

        //подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee1();
        //начало теста
        employeeService.put(firstName, lastName, departmentId, salaryEmployee);
        Employee actualEmployee = employeeService.find(firstName, lastName, departmentId, salaryEmployee);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void find_EmployeeNotFoundException() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY_1;
        //подготовка ожидаемого результата
        String exceptionMessage = "404 данный сотрудник не найден";
        //начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.find(firstName, lastName, departmentId, salaryEmployee)
        );
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void getAll() {
        //подготовка входных данных
        String firstName = FIRST_NAME_1;
        String lastName = LAST_NAME_1;
        double salaryEmployee = SALARY_1;
        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salaryEmployee2 = SALARY_2;
        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salaryEmployee3 = SALARY_3;

        Integer departmentId = FIRST_DEPARTMENT_ID;
        Integer lastdepartmentId = LAST_DEPARTMENT_ID;
        Employee firstEmployee = getEmployee1();
        Employee secondEmployee = getEmployee2();
        Employee lastEmployee = getEmployee3();


        //подготовка ожидаемого результата
        List<Employee> expectedEmployee = List.of(firstEmployee, secondEmployee,lastEmployee);
        //начало теста
        employeeService.put(FIRST_NAME_3, LAST_NAME_3, LAST_DEPARTMENT_ID, SALARY_3);
        employeeService.put(FIRST_NAME_2, LAST_NAME_2, FIRST_DEPARTMENT_ID, SALARY_2);
        employeeService.put(FIRST_NAME_1, LAST_NAME_1,FIRST_DEPARTMENT_ID, SALARY_1);
        List<Employee> actualEmployee = employeeService.getAll();
        assertEquals(expectedEmployee.stream().sorted(),actualEmployee.stream().sorted());


    }
}