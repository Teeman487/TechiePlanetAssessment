package akinade;

import org.springframework.boot.SpringApplication;

public class TestStudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(StudentServiceApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
