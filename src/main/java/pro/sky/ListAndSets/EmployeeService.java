package pro.sky.ListAndSets;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
   private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        employees.add(newEmployee);
        return newEmployee;

    }
    public Employee remove(String firstName, String lastName) {
        Employee removeEmployee = new Employee(firstName, lastName);
        employees.remove(removeEmployee);
        return removeEmployee;
    }
    public Employee find(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (employees.contains(findEmployee)){
        return findEmployee;}
        return null;
    }
    public List<Employee> getAll(){
        return employees;

    }
}
