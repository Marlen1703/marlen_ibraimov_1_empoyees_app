package marlen_ibraimov_1_employees_app.controller;

import marlen_ibraimov_1_employees_app.dto.EmployeeDto;
import marlen_ibraimov_1_employees_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employees-rest")
 public class RestEmployeeController  {

    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public Page<EmployeeDto> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size){
        Page<EmployeeDto> employeeDtos=employeeService.getAllEmployees(page, size);
        return employeeDtos;
    }


    @PutMapping(value = "/updateEmployee/{id}")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto body,@PathVariable long id){
        if(body.getName()=="" ||body.getSurname()=="" ||body.getDepartment()=="" ||
                Integer.toString(body.getSalary())==""){
            return ResponseEntity.badRequest().build();
        }
        employeeService.updateEmployee(body,id);
        return ResponseEntity.ok("Success");
    }

    @PostMapping(value = "/new")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(new EmployeeDto(employeeService.createEmployee(employeeDto)));
    }

    @DeleteMapping(value = "deleteEmployee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Deleted");
    }
}
