package marlen_ibraimov_1_employees_app.controller;

import marlen_ibraimov_1_employees_app.dto.EmployeeDto;
import marlen_ibraimov_1_employees_app.repository.EmployeeRepository;
import marlen_ibraimov_1_employees_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employees")
public class ViewEmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping()
    public String getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,Model model){
        model.addAttribute("counts", employeeService.getAllEmployees(page,size));
        return "employees";
    }


    @GetMapping(value = "/updateEmployee/{id}")
    public String updateEmployeeView(@PathVariable Long id){
        return "updateEmployee";
    }

    @PutMapping(value = "/updateEmployee/{id}")
    public String updateEmployee(@RequestBody EmployeeDto body,@PathVariable long id){

        if(body.getName()=="" || body.getSurname()=="" || body.getDepartment()=="" ||
                Integer.toString(body.getSalary())==""){
            return "updateEmployee";
        }
        employeeService.updateEmployee(body,id);
        return "updateEmployee";
    }

    @GetMapping(value = "/new")
    public String createEmployee(){
        return "createEmployee";
    }

    @PostMapping(value = "/new")
    public String createEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.createEmployee(employeeDto);
        return "createEmployee";
    }

    @DeleteMapping(value = "deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "employees";
    }

}
