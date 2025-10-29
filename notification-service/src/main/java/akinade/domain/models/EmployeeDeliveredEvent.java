package akinade.domain.models;

import java.time.LocalDateTime;

public record EmployeeDeliveredEvent (
        String eventId,
        String employeeCode,
        Manager manager,
        LocalDateTime createdAt

) {
}
