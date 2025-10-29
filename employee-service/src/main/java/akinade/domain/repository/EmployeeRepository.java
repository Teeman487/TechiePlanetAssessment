package akinade.domain.repository;

import akinade.domain.dto.Status;
import akinade.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailAndDepartmentId(String email, Long departmentId);
    List<Employee> findByDepartmentId(Long departmentId); // Select * from employees Where department_id = ?;
    List<Employee> findByStatus(Status status);

    Optional<Employee> findByEmployeeCode(String employeeCode);

    default void updateEmployeeStatus(String employeeCode, Status status) {
        Employee employee = this.findByEmployeeCode(employeeCode).orElseThrow();
        employee.setStatus(status);
        this.save(employee);
    }

}
