package akinade.domain.dto;

import java.time.LocalDateTime;

public record EmployeeCancelledEvent (
        String eventId,
        String employeeCode,
        String reason,
        LocalDateTime createdAt )
{
}
