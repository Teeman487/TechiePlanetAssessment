package akinade.domain.client;


import akinade.domain.dto.StudentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class StudentClient {


    @Value("${student.service.url}") // http://student-service:8080
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public StudentResponse getStudent(UUID id) {
        return restTemplate.getForObject(baseUrl + "/api/students/" +
                id, StudentResponse.class);
    }

    public StudentResponse[] getAllStudents() {
        return restTemplate.getForObject(baseUrl + "/api/students",
                StudentResponse[].class);
    }

}
