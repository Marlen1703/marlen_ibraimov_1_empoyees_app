package marlen_ibraimov_1_employees_app.service;

import marlen_ibraimov_1_employees_app.dto.EmployeeDto;
import marlen_ibraimov_1_employees_app.entity.Employee;
import marlen_ibraimov_1_employees_app.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper mapper;

    public Page<EmployeeDto> getAllEmployees(int page, int size){
        Pageable firstPageWithTenElements = PageRequest.of(page, size);
        Page<EmployeeDto> employeeDtos=employeeRepository.findAll(firstPageWithTenElements).map(EmployeeDto::new);
        return employeeDtos;
    }

    public void updateEmployee(EmployeeDto employeeDto,long id){
        Optional<Employee> employeeExist=employeeRepository.findById(id);
        employeeExist.get().setName(employeeDto.getName());
        employeeExist.get().setSurname(employeeDto.getSurname());
        employeeExist.get().setDepartment(employeeDto.getDepartment());
        employeeExist.get().setSalary(Integer.valueOf(employeeDto.getSalary()));
        employeeRepository.save(employeeExist.get());
    }
    public Employee createEmployee(EmployeeDto employeeDto){
        Employee employee=mapper.map(employeeDto,Employee.class);
        employeeRepository.save(employee);
        return employee;
    }
    public void deleteEmployee(Long id){
        Optional<Employee> employeeExist=employeeRepository.findById(id);
        employeeRepository.delete(employeeExist.get());
    }

}
