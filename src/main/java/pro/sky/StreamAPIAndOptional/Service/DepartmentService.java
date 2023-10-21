package pro.sky.StreamAPIAndOptional.Service;

import pro.sky.StreamAPIAndOptional.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Map<Integer, List<Employee>>getEmployeesDepartments(Integer departmentsId);
    Employee getEmployeeMinSalary(Integer departmentsId);
    Employee getEmployeeMaxSalary(Integer departmentsId);


}
