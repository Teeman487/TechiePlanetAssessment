package akinade.notifications;

import org.springframework.boot.SpringApplication;

public class TestNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(NotificationServiceApplicationTests::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
