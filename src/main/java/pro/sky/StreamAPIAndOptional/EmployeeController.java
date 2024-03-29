package pro.sky.StreamAPIAndOptional;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @ExceptionHandler(RuntimeException.class)
    public String handlerException (RuntimeException e){
        return e.getMessage();
    }
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int departmentNumber){
        return employeeService.put(firstName,lastName,departmentNumber);

    }
    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int departmentNumber){
        return employeeService.remove(firstName,lastName,departmentNumber);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName,@RequestParam int departmentNumber){
        return employeeService.find(firstName,lastName,departmentNumber);
    }

    @GetMapping
    public Collection<Employee> getAll(){
        return employeeService.getAll();
    }






}
