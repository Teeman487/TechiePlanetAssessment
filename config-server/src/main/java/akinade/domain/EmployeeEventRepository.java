package akinade.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, Long> {
    boolean existsByEventId(String eventId);
}
