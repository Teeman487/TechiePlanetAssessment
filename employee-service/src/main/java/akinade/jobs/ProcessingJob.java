package akinade.jobs;
import akinade.domain.service.impl.EmployeeServiceImpl;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class ProcessingJob {
    private static final Logger log = LoggerFactory.getLogger(ProcessingJob.class);

    private final EmployeeServiceImpl employeeService;

    ProcessingJob(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "${orders.new-orders-job-cron}")
    @SchedulerLock(name = "processNewOrders")

    public void processNewOrders() {
        LockAssert.assertLocked();
        log.info("Processing new orders at {}", Instant.now());
        employeeService.newProcess();
    }
}
