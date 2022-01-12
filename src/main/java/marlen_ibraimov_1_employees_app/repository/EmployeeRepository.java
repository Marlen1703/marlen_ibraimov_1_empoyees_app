package marlen_ibraimov_1_employees_app.repository;

import marlen_ibraimov_1_employees_app.dto.EmployeeDto;
import marlen_ibraimov_1_employees_app.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {


    @Query(value = "select * from employees where extract(day from timestamp)=extract(day from current_date)",nativeQuery = true)
    Page<EmployeeDto> findAllByToDayDate();

}
