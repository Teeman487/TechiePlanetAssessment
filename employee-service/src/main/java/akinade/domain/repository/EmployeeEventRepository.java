package akinade.domain.repository;

import akinade.domain.EmployeeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, Long> {
}
