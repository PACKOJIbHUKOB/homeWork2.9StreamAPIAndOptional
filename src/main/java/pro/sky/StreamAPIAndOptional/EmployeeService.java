package pro.sky.StreamAPIAndOptional;

import java.util.Collection;

public interface EmployeeService {
    Employee put(String firstName, String lastName, int departmentNumber);

    Employee remove(String firstName, String lastName, int departmentNumber);

    Employee find(String firstName, String lastName, int departmentNumber);

    Collection<Employee> getAll();


}
