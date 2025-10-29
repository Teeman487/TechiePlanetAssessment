package akinade;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "akinade")
public record ApplicationProperties(
        String catalogServiceUrl,
        String employeeEventsExchange,
        String newEmployeesQueue,
        String errorEmployeesQueue) {}
