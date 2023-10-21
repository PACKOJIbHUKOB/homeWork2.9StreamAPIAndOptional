package pro.sky.StreamAPIAndOptional.Service;

import org.springframework.stereotype.Service;
import pro.sky.StreamAPIAndOptional.Employee;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesDepartments(Integer departmentsId) {

        return employeeService.getAll()
                .stream()
                .filter (e -> departmentsId==null||e.getDepartmentNumber() == departmentsId)
                .collect(groupingBy(Employee::getDepartmentNumber, toList()));
    }
    @Override
    public Employee getEmployeeMinSalary(Integer departmentsId) {
        return  employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartmentNumber() == departmentsId)
                .min(Comparator.comparing(Employee::getSalaryEmployee))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }
    @Override
    public Employee getEmployeeMaxSalary(Integer departmentsId) {
            return  employeeService.getAll()
                    .stream()
                    .filter(e -> e.getDepartmentNumber() == departmentsId)
                    .max(Comparator.comparing(Employee::getSalaryEmployee))
                    .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }
}
