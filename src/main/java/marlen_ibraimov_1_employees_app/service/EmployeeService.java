package marlen_ibraimov_1_employees_app.service;

import marlen_ibraimov_1_employees_app.entity.Employee;
import marlen_ibraimov_1_employees_app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(int page,int size){
        Pageable firstPageWithTenElements = PageRequest.of(page, size);
        Page <Employee> employeePage=employeeRepository.findAll(firstPageWithTenElements);
        return employeePage;
    }

    public Optional<Employee> deleteEmployeeById(Long id){
        Optional<Employee> employeeExist=employeeRepository.findById(id);
        employeeRepository.delete(employeeExist.get());
        return employeeExist;
    }

    public void updateEmployee(String name, String surname, String department, String salary, Long id){
        Optional<Employee> employeeExist=employeeRepository.findById(id);
        employeeExist.get().setName(name);
        employeeExist.get().setSurname(surname);
        employeeExist.get().setDepartment(department);
        employeeExist.get().setSalary(Integer.valueOf(salary));
        employeeRepository.save(employeeExist.get());
    }
    public Employee createEmployee(String name,String surname,String department,String salary){
        Employee employee=Employee.builder().
                name(name).
                surname(surname).
                department(department).
                salary(Integer.valueOf(salary)).
                build();
        employeeRepository.save(employee);
        return employee;
    }
    public void deleteEmployee(Long id){
        Optional<Employee> employeeExist=employeeRepository.findById(id);
        employeeRepository.delete(employeeExist.get());
    }

}
