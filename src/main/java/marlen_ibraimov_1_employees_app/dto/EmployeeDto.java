package marlen_ibraimov_1_employees_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marlen_ibraimov_1_employees_app.entity.Employee;


@Getter
@Setter
public class EmployeeDto {

    private Long id;

    private String name;

    private String surname;

    private String department;

    private int salary;

    public EmployeeDto(Employee employee) {
        this.id=employee.getId();
        this.name=employee.getName();
        this.surname=employee.getSurname();
        this.department=employee.getDepartment();
        this.salary=employee.getSalary();
    }
}
