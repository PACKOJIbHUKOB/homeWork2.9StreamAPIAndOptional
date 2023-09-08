package pro.sky.MnogoobrazieAndRealizaciya;

import org.springframework.stereotype.Service;
import pro.sky.MnogoobrazieAndRealizaciya.exceptions.EmployeeAlreadyAddedException;
import pro.sky.MnogoobrazieAndRealizaciya.exceptions.EmployeeNotFoundException;
import pro.sky.MnogoobrazieAndRealizaciya.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    private static final int MaxSize =2;

    private final Map<String,Employee> employees = new HashMap<>();

    public Employee put(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(newEmployee.getKey())){
            throw new EmployeeAlreadyAddedException("данный сотрудник уже существует");
        }
        if (employees.size()>MaxSize){
            throw new EmployeeStorageIsFullException("Нет места для добавления сотрудника");
        }
        employees.put(newEmployee.getKey(), newEmployee);
        return newEmployee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee removeEmployee = new Employee(firstName, lastName);
        if (!employees.containsKey(removeEmployee.getKey())){
            throw new EmployeeNotFoundException("невозможно удалить,данный сотрудник не найден");}
        employees.remove(removeEmployee.getKey());
        return removeEmployee;
    }

    public Employee find(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(findEmployee.getKey())){
            return findEmployee;}
        else {
            throw new EmployeeNotFoundException("данный сотрудник не найден");
        }

    }
    public Collection<Employee> getAll(){
        return Collections.unmodifiableCollection(employees.values());

    }
}
