package pro.sky.StreamAPIAndOptional.Service;

import pro.sky.StreamAPIAndOptional.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Employee put(String firstName, String lastName, int departmentNumber,double salaryEmployee);

    Employee remove(String firstName, String lastName, int departmentNumber,double salaryEmployee);

    Employee find(String firstName, String lastName, int departmentNumber,double salaryEmployee);

    List<Employee> getAll();
    double randomSalary();


}
