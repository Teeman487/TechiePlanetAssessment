package akinade.domain.models;

import java.time.LocalDateTime;

public record EmployeeCancelledEvent (
        String eventId,
        String employeeCode,
        Manager manager,
        String reason,
        LocalDateTime createdAt
)

{
}
