package akinade.domain.dto;

import java.time.LocalDateTime;

public record EmployeeCreatedEvent(
        String eventId,
        String employeeCode,
        LocalDateTime createdAt
) { }
