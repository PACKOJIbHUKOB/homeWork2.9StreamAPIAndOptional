package pro.sky.StreamAPIAndOptional.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.StreamAPIAndOptional.Employee;
import pro.sky.StreamAPIAndOptional.Service.EmployeeService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int departmentNumber){
        return employeeService.put(firstName,lastName,departmentNumber,randomSalary());

    }
    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int departmentNumber){
        return employeeService.remove(firstName,lastName,departmentNumber,randomSalary());
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName,@RequestParam int departmentNumber){
        return employeeService.find(firstName,lastName,departmentNumber,randomSalary());
    }

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    private double randomSalary(){
        return employeeService.randomSalary();
    }






}
