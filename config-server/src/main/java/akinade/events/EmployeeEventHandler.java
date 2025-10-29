package akinade.events;

import akinade.domain.EmployeeEvent;
import akinade.domain.EmployeeEventRepository;
import akinade.domain.NotificationService;

import akinade.domain.models.EmployeeCancelledEvent;
import akinade.domain.models.EmployeeCreatedEvent;
import akinade.domain.models.EmployeeDeliveredEvent;
import akinade.domain.models.EmployeeErrorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EmployeeEventHandler {
    private static final Logger log = LoggerFactory.getLogger(EmployeeEventHandler.class);

    private final NotificationService notificationService;
    private final EmployeeEventRepository employeeEventRepository;

    public EmployeeEventHandler(NotificationService notificationService, EmployeeEventRepository employeeEventRepository) {
        this.notificationService = notificationService;
        this.employeeEventRepository = employeeEventRepository;
    }

    @RabbitListener(queues = "${notification.new-employees-queue}")
    public void handle(EmployeeCreatedEvent event) {
        if (employeeEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate employeCreatedEvent with eventId: {}", event.eventId());
            return;
        }
        log.info("Received a employeCreatedEvent with employeeNumber:{}: ", event.employeeCode());
        notificationService.sendEmployeeCreatedNotification(event);
        var employeeEvent = new EmployeeEvent(event.eventId());
        employeeEventRepository.save(employeeEvent);
    }

    @RabbitListener(queues = "${notification.delivered-employees-queue}")
    public void handle(EmployeeDeliveredEvent event) {
        if (employeeEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate EmployeeDeliveredEvent with eventId: {}", event.eventId());
            return;
        }
        log.info("Received a EmployeeDeliveredEvent with employeeCode:{}: ", event.employeeCode());
        notificationService.sendEmployeeDeliveredNotification(event);
        var employeEvent = new EmployeeEvent(event.eventId());
        employeeEventRepository.save(employeEvent);
    }

    @RabbitListener(queues = "${notification.cancelled-employees-queue}")
    public void handle(EmployeeCancelledEvent event) {
        if (employeeEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate EmployeeCancelledEvent with eventId: {}", event.eventId());
            return;
        }
        notificationService.sendEmployeeCancelledNotification(event);
        log.info("Received a EmployeeCancelledEvent with employeeCode:{}: ", event.employeeCode());
        var employeEvent = new EmployeeEvent(event.eventId());
        employeeEventRepository.save(employeEvent);
    }

    @RabbitListener(queues = "${notification.error-employees-queue}")
    public void handle(EmployeeErrorEvent event) {
        if (employeeEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate employeeErrorEvent with eventId: {}", event.eventId());
            return;
        }
        log.info("Received a EmployeeErrorEvent with employeeCode:{}: ", event.employeeCode());
        notificationService.sendEmployeeErrorEventNotification(event);
        EmployeeEvent employeEvent = new EmployeeEvent(event.eventId());
        employeeEventRepository.save(employeEvent);
    }
}
