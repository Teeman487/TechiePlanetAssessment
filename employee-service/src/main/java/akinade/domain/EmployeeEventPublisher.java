package akinade.domain;

import akinade.ApplicationProperties;
import akinade.domain.dto.EmployeeCreatedEvent;
import akinade.domain.dto.EmployeeErrorEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventPublisher {
     final RabbitTemplate rabbitTemplate;
     final ApplicationProperties properties;

     EmployeeEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
         this.rabbitTemplate = rabbitTemplate;
         this.properties = properties;
     }

    public void publish(EmployeeCreatedEvent event) {
        this.send(properties.newEmployeesQueue(), event);
    }

    public void publish(EmployeeErrorEvent event) {
        this.send(properties.errorEmployeesQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.employeeEventsExchange(), routingKey, payload);
    }
}
