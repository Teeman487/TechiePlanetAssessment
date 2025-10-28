package akinade.domain.repository;

import akinade.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailAndDepartmentId(String email, Long departmentId);
    List<Employee> findByDepartmentId(Long departmentId); // Select * from employees Where department_id = ?;
}
