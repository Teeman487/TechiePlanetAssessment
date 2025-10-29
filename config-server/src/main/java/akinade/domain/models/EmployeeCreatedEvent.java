package akinade.domain.models;

import java.time.LocalDateTime;


public record EmployeeCreatedEvent (
        String eventId,
        String employeeCode,
        Manager manager,
        LocalDateTime createdAt
)

{
}
