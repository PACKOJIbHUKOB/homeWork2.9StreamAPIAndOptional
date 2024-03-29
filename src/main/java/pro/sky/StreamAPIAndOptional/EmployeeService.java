package pro.sky.StreamAPIAndOptional;

import org.springframework.stereotype.Service;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeAlreadyAddedException;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeNotFoundException;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    private static final int MaxSize =2;

    private final Map<String,Employee> employees = new HashMap<>();

    public Employee put(String firstName, String lastName, int departmentNumber) {
        Employee newEmployee = new Employee(firstName,lastName,departmentNumber);
        String key = getKey(firstName,lastName,departmentNumber);
        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException("данный сотрудник уже существует");
        }
        if (employees.size()>MaxSize){
            throw new EmployeeStorageIsFullException("Нет места для добавления сотрудника");
        }
        int newId= newEmployee.getID();
        newEmployee.setCounter(newId+1);
        employees.put(key, newEmployee);
        return newEmployee;
    }

    public Employee remove(String firstName, String lastName, int departmentNumber) {
        Employee removeEmployee = new Employee(firstName,lastName,departmentNumber);
        String key = getKey(firstName,lastName,departmentNumber);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException("невозможно удалить,данный сотрудник не найден");}
        employees.remove(key);
        return removeEmployee;
    }

    public Employee find(String firstName, String lastName, int departmentNumber) {
        Employee findEmployee = new Employee(firstName,lastName,departmentNumber);
        String key = getKey(firstName,lastName,departmentNumber);
        if (employees.containsKey(key)){
            return findEmployee;}
        else {
            throw new EmployeeNotFoundException("данный сотрудник не найден");
        }

    }
    public Collection<Employee> getAll(){
        return Collections.unmodifiableCollection(employees.values());
    }
    private String getKey(String firstName, String lastName, int departmentNumber){
        return firstName+lastName+departmentNumber;
    }
}
