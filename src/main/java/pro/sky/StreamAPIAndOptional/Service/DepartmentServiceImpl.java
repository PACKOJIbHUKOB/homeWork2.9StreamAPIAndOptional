package pro.sky.StreamAPIAndOptional.Service;

import org.springframework.stereotype.Service;
import pro.sky.StreamAPIAndOptional.Employee;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployeesDepartments(Integer departmentsId) {

        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartmentNumber() == departmentsId)
                .collect(Collectors.toList());
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
    @Override
    public Map<Integer, List<Employee>> getEmployees() {
        return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentNumber));
    }
}
