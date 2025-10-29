package akinade.domain;

import akinade.ApplicationProperties;
import akinade.domain.models.EmployeeCancelledEvent;
import akinade.domain.models.EmployeeCreatedEvent;
import akinade.domain.models.EmployeeDeliveredEvent;
import akinade.domain.models.EmployeeErrorEvent;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final JavaMailSender emailSender;
    private final ApplicationProperties properties;

    public NotificationService(JavaMailSender emailSender, ApplicationProperties properties) {
        this.emailSender = emailSender;
        this.properties = properties;
    }

    public void sendEmployeeCreatedNotification(EmployeeCreatedEvent event) {
        String message = """
                ===================================================
                🟢 Employee Creation Notification
                ---------------------------------------------------
                Dear %s,

                A new employee has been successfully created in your department.

                Employee Details:
                - Employee Code: %s
             
                Please ensure the employee onboarding process is completed accordingly.

                Regards,
                HR Management Team
                ===================================================
                """.formatted(
                event.manager().name(),
                event.employeeCode()
        );

        log.info("\n{}", message);
        sendEmail(event.manager().email(), "Employee Created - " + event.employeeCode(), message);
    }

    public void sendEmployeeDeliveredNotification(EmployeeDeliveredEvent event) {
        String message = """
                ===================================================
                🟢 Employee Delivery Confirmation
                ---------------------------------------------------
                Dear %s,

                The onboarding process for employee (Code: %s) has been successfully completed.
              
                Thank you for confirming the successful onboarding.

                Regards,
                HR Management Team
                ===================================================
                """.formatted(
                event.manager().name(),
                event.employeeCode()
        );

        log.info("\n{}", message);
        sendEmail(event.manager().email(), "Employee Onboarding Completed - " + event.employeeCode(), message);
    }


    public void sendEmployeeCancelledNotification(EmployeeCancelledEvent event) {
        String message = """
                ===================================================
                🔴 Employee Process Cancelled
                ---------------------------------------------------
                Dear %s,

                The employee process for employeeCode: %s has been cancelled.

                Reason: %s

                Please review and take necessary action if required.

                Regards,
                HR Management Team
                ===================================================
                """.formatted(event.manager().name(), event.employeeCode(), event.reason());

        log.info("\n{}", message);
        sendEmail(event.manager().email(), "Employee Process Cancelled - " + event.employeeCode(), message);
    }

    public void sendEmployeeErrorEventNotification(EmployeeErrorEvent event) {
        String message = """
                ===================================================
                ⚠️ Employee Processing Failure
                ---------------------------------------------------
                Hello Support Team,

                An error occurred while processing employee (Code: %s).

                Details:
                - Error Reason: %s

                Please investigate this issue promptly.

                Regards,
                Automated HR Notification System
                ===================================================
                """.formatted(event.employeeCode(), event.reason());

        log.error("\n{}", message);
        sendEmail(properties.supportEmail(), "Employee Processing Failure - " + event.employeeCode(), message);
    }


    private void sendEmail(String recipient, String subject, String content) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(properties.supportEmail());
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(content, false);
            emailSender.send(mimeMessage);

            log.info(" Email sent successfully to: {}", recipient);
        } catch (Exception e) {
            log.error(" Error while sending email to {}: {}", recipient, e.getMessage());
            throw new RuntimeException("Error while sending email", e);
        }
    }
}

