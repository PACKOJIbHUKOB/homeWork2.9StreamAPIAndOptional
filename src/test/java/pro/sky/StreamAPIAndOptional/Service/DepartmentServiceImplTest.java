package pro.sky.StreamAPIAndOptional.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.StreamAPIAndOptional.Employee;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.StreamAPIAndOptional.Service.UnitTest.GenerateTest.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Test
    void getEmployeesDepartments_withDepartmentId() {
        //подготовка входных данных
        Integer departmentsId = FIRST_DEPARTMENT_ID;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());
        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(departmentsId, Arrays.asList(getEmployee1(), getEmployee2()));

        //начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesDepartments(departmentsId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee1().getDepartmentNumber(), getEmployee2().getDepartmentNumber());
        assertNotEquals(getEmployee1().getDepartmentNumber(), getEmployee3().getDepartmentNumber());
    }
        @Test
    void getEmployeesDepartments_withOutDepartmentId() {
        //подготовка входных данных
        Integer departmentsId = null;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());
        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, Arrays.asList(getEmployee1(), getEmployee2()));
        expectedMap.put(LAST_DEPARTMENT_ID, Collections.singletonList(getEmployee3()));

        //начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesDepartments(departmentsId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee1().getDepartmentNumber(), getEmployee2().getDepartmentNumber());
        assertNotEquals(getEmployee1().getDepartmentNumber(), getEmployee3().getDepartmentNumber());
    }
    @Test
    void getEmployeeMinSalary_susses() {
        //подготовка входных данных
        Integer departmentID = FIRST_DEPARTMENT_ID;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());
        Employee expectedEmploy = getEmployee1();

        //начало теста
        Employee actualEmployee = departmentService.getEmployeeMinSalary(departmentID);
        assertEquals(expectedEmploy,actualEmployee);
        assertTrue(getEmployee1().getSalaryEmployee()<getEmployee2().getSalaryEmployee());
    }
    @Test
    void getEmployMinSalary_exceptionEmployNotFoundException(){
        //подготовка входных данных
        Integer departmenId= LAST_DEPARTMENT_ID;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage ="404 Сотрудник не найден";

        //начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                ()-> departmentService.getEmployeeMinSalary(departmenId)
        );
        assertEquals(expectedMessage,exception.getMessage());
    }
    @Test
    void getEmployeeMaxSalary_susses() {
        //подготовка входных данных
        Integer departmentID = FIRST_DEPARTMENT_ID;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());
        Employee expectedEmploy = getEmployee2();

        //начало теста
        Employee actualEmployee = departmentService.getEmployeeMaxSalary(departmentID);
        assertEquals(expectedEmploy,actualEmployee);
        assertTrue(getEmployee1().getSalaryEmployee()<getEmployee2().getSalaryEmployee());
    }
    @Test
    void getEmployeeMaxSalary_exceptionEmployeeNotFoundException() {
        //подготовка входных данных
        Integer departmenId= LAST_DEPARTMENT_ID;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage ="404 Сотрудник не найден";
        //начало теста

        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                ()-> departmentService.getEmployeeMaxSalary(departmenId)
        );
        assertEquals(expectedMessage,exception.getMessage());
    }
}