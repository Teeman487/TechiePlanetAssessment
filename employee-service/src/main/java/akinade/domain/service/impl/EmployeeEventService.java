package akinade.domain.service.impl;

import akinade.domain.EmployeeEvent;
import akinade.domain.EmployeeEventPublisher;
import akinade.domain.EmployeeEventType;
import akinade.domain.dto.EmployeeCancelledEvent;
import akinade.domain.dto.EmployeeCreatedEvent;
import akinade.domain.dto.EmployeeErrorEvent;
import akinade.domain.repository.EmployeeEventRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeEventService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeEventService.class);

    final EmployeeEventRepository employeeEventRepository;
    final EmployeeEventPublisher employeeEventPublisher;
    final ObjectMapper objectMapper;

    public EmployeeEventService(EmployeeEventRepository employeeEventRepository,
                                EmployeeEventPublisher employeeEventPublisher, ObjectMapper objectMapper) {
        this.employeeEventRepository = employeeEventRepository;
        this.employeeEventPublisher = employeeEventPublisher;
        this.objectMapper = objectMapper;
    }



    void save(EmployeeCreatedEvent event) {
        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEventId(event.eventId());
        employeeEvent.setEventType(EmployeeEventType.EMPLOYEE_CREATED);
        employeeEvent.setEmployeeCode(event.employeeCode());
        employeeEvent.setCreatedAt(event.createdAt());
        employeeEvent.setPayload(toJsonPayload(event));
        this.employeeEventRepository.save(employeeEvent);
    }
    void save(EmployeeCancelledEvent event) {
        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEventId(event.eventId());
        employeeEvent.setEventType(EmployeeEventType.EMPLOYEE_CREATED);
        employeeEvent.setEmployeeCode(event.employeeCode());
        employeeEvent.setCreatedAt(event.createdAt());
        employeeEvent.setPayload(toJsonPayload(event));
        this.employeeEventRepository.save(employeeEvent);
    }

    void save(EmployeeErrorEvent event) {
        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEventId(event.eventId());
        employeeEvent.setEventType(EmployeeEventType.EMPLOYEE_PROCESSING_FAILED);
        employeeEvent.setEmployeeCode(event.employeeCode());
        employeeEvent.setCreatedAt(event.createdAt());
        employeeEvent.setPayload(toJsonPayload(event));
        this.employeeEventRepository.save(employeeEvent);
    }


    public void publishEmployeeEvents() {
        Sort sort = Sort.by("createdAt").ascending();
        List<EmployeeEvent> events = employeeEventRepository.findAll(sort);
        for (EmployeeEvent event : events) {
            this.publishEvent(event);           // publish event() to RabbitMQ(Main Exchange-QUEUE)
            employeeEventRepository.delete(event);
        }
    }





    private void publishEvent(EmployeeEvent event) {
        EmployeeEventType eventType = event.getEventType();
        switch (eventType) {
            case EMPLOYEE_CREATED:
                EmployeeCreatedEvent employeeCreatedEvent = fromJsonPayload(event.getPayload(), EmployeeCreatedEvent.class);
                employeeEventPublisher.publish(employeeCreatedEvent);
                break;

            case EMPLOYEE_PROCESSING_FAILED:
                EmployeeErrorEvent employeeErrorEvent = fromJsonPayload(event.getPayload(), EmployeeErrorEvent.class);
                employeeEventPublisher.publish(employeeErrorEvent);
                break;

            default:
                log.warn("Unsupported EmployeeEventType: {}", eventType);
        }
    }

    private String toJsonPayload(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T fromJsonPayload(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
