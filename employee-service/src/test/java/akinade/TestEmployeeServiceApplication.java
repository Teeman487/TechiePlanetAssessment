package akinade;

import org.springframework.boot.SpringApplication;

public class TestEmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(EmployeeServiceApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
