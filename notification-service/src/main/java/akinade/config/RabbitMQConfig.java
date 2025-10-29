package akinade.config;

import akinade.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMQConfig {
    private final ApplicationProperties properties;

    RabbitMQConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(properties.employeeEventsExchange());
    }

    @Bean
    Queue newemployeesQueue() {
        return QueueBuilder.durable(properties.newEmployeesQueue()).build();
    }

    @Bean
    Binding newemployeesQueueBinding() {
        return BindingBuilder.bind(newemployeesQueue()).to(exchange()).with(properties.newEmployeesQueue());
    }

    @Bean
    Queue deliveredemployeesQueue() {
        return QueueBuilder.durable(properties.deliveredEmployeesQueue()).build();
    }

    @Bean
    Binding deliveredemployeesQueueBinding() {
        return BindingBuilder.bind(deliveredemployeesQueue()).to(exchange()).with(properties.deliveredEmployeesQueue());
    }

    @Bean
    Queue cancelledemployeesQueue() {
        return QueueBuilder.durable(properties.cancelledEmployeesQueue()).build();
    }

    @Bean
    Binding cancelledemployeesQueueBinding() {
        return BindingBuilder.bind(cancelledemployeesQueue()).to(exchange()).with(properties.cancelledEmployeesQueue());
    }

    @Bean
    Queue erroremployeesQueue() {
        return QueueBuilder.durable(properties.errorEmployeesQueue()).build();
    }

    @Bean
    Binding erroremployeesQueueBinding() {
        return BindingBuilder.bind(erroremployeesQueue()).to(exchange()).with(properties.errorEmployeesQueue());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }
}
