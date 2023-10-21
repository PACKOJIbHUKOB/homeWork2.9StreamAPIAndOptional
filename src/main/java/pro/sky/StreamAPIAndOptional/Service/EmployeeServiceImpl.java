package pro.sky.StreamAPIAndOptional.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.StreamAPIAndOptional.Employee;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeAlreadyAddedException;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeNotFoundException;
import pro.sky.StreamAPIAndOptional.exceptions.EmployeeStorageIsFullException;
import pro.sky.StreamAPIAndOptional.exceptions.InvalidException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_SIZE =2;

    public Map<String,Employee> employees = new HashMap<>(Map.of());


    @Override
    public Employee put(String firstName, String lastName, int departmentNumber,double salaryEmployee) {
        Employee newEmployee = new Employee(firstName,lastName,departmentNumber,salaryEmployee);
        String key = getKey(firstName,lastName,departmentNumber);
        chekFirstNameAndLastName(firstName,lastName);
        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException("данный сотрудник уже существует");
        }
        if (employees.size()> MAX_SIZE){
            throw new EmployeeStorageIsFullException("Нет места для добавления сотрудника");
        }
//        int newId= newEmployee.getID();
//        newEmployee.setCounter(newId+1);
        employees.put(key, newEmployee);
        return newEmployee;
    }

    @Override
    public Employee remove(String firstName, String lastName, int departmentNumber,double salaryEmployee) {
        Employee removeEmployee = new Employee(firstName,lastName,departmentNumber, salaryEmployee);
        String key = getKey(firstName,lastName,departmentNumber);
        chekFirstNameAndLastName(firstName,lastName);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException("невозможно удалить,данный сотрудник не найден");}
        employees.remove(key);
        return removeEmployee;
    }

    @Override
    public Employee find(String firstName, String lastName, int departmentNumber,double salaryEmployee) {
        Employee findEmployee = new Employee(firstName, lastName, departmentNumber, salaryEmployee);
        String key = getKey(firstName, lastName, departmentNumber);
        chekFirstNameAndLastName(firstName, lastName);
        if (employees.containsKey(key)) {
            return findEmployee;
        } else {
            throw new EmployeeNotFoundException("данный сотрудник не найден");
        }
    }
    @Override
    public List<Employee> getAll(){
        return new ArrayList<>(employees.values());
    }
     private String getKey(String firstName, String lastName, int departmentNumber) {
        return firstName + lastName + departmentNumber;
    }
    private void chekFirstNameAndLastName (String firstName, String lastName){
        if  (!StringUtils.isAlpha(firstName)&&StringUtils.isAlpha(lastName)||StringUtils.isBlank(firstName)&&StringUtils.isBlank(lastName)){
            throw new InvalidException("Введенные данные имеют запрещенные символы");}
    }

    public double randomSalary(){
        double min = Math.ceil(1);
        double max = Math.floor(100000);
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }



}
