package akinade.jobs;


import akinade.domain.service.impl.EmployeeEventService;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class EventsPublishingJob {
    private static final Logger log = LoggerFactory.getLogger(EventsPublishingJob.class);

    private final EmployeeEventService employeeEventService;

    EventsPublishingJob(EmployeeEventService employeeEventService) {
        this.employeeEventService = employeeEventService;
    }

    // we want at least only one node should start processing these events and should not be duplicated accross multiple nodes
    @Scheduled(cron = "${publish-events-job-cron}")
    @SchedulerLock(name = "publishEvents")
    public void publishOrderEvents() {
        LockAssert.assertLocked();  // Assert that the method has acquired the distributed lock
        log.info("Publishing Events at {}", Instant.now());
        employeeEventService.publishEmployeeEvents();
    }
}


