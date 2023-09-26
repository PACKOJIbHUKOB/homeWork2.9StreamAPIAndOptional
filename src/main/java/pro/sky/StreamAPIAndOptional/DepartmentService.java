package pro.sky.StreamAPIAndOptional;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getEmployeesDepartments(Integer departmentsId);
    Employee getEmployeeMinSalary(Integer departmentsId);
    Employee getEmployeeMaxSalary(Integer departmentsId);
    Map<Integer, List<Employee>> getEmployees();

}
