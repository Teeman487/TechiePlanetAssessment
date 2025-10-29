package akinade.domain.dto;

import java.time.LocalDateTime;

public record EmployeeErrorEvent (
        String eventId,
        String employeeCode,
        String reason,
        LocalDateTime createdAt
)
{
}
