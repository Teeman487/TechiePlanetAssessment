package akinade.domain.models;

import java.time.LocalDateTime;

public record EmployeeErrorEvent (
        String eventId,
        String employeeCode,
        String reason,
        LocalDateTime createdAt
)

{
}
