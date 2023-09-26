package pro.sky.StreamAPIAndOptional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
   private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path ="/max-salary")
    public Employee getEmployeeMaxSalary (@RequestParam Integer departmentsId){
        return departmentService.getEmployeeMaxSalary(departmentsId);
    }
    @GetMapping(path ="/min-salary")
    public Employee getEmployeeMinSalary (@RequestParam Integer departmentsId) {
        return departmentService.getEmployeeMinSalary(departmentsId);
    }
    @GetMapping(value = "/all",params = {"departmentsId"})
    public Collection<Employee> getEmployees(@RequestParam Integer departmentsId ){
        return departmentService.getEmployeesDepartments(departmentsId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployees (){
        return departmentService.getEmployees();

    }




}
