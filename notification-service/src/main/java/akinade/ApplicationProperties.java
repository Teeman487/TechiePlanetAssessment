package akinade;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "notification")
public record ApplicationProperties(
        String employeeEventsExchange,
        String newEmployeesQueue,
        String deliveredEmployeesQueue,
        String cancelledEmployeesQueue,
        String errorEmployeesQueue,
        String supportEmail) {}
