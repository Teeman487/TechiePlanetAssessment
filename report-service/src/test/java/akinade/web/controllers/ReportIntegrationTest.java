package akinade.web.controllers;

import akinade.ContainersConfig;
import akinade.domain.client.StudentClient;
import akinade.domain.dto.StudentResponse;
import akinade.domain.dto.SubjectScoreDTO;
import akinade.domain.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
class ReportIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StudentClient studentClient; // mock external call

    @Test
    void testGetReports() {

        // Mock the external StudentClient response
        StudentResponse[] mockStudents = new StudentResponse[]{
                new StudentResponse(
                        UUID.randomUUID(),
                        "Azeez",
                        List.of(
                                new SubjectScoreDTO(Subject.MATH, 90),
                                new SubjectScoreDTO(Subject.ENGLISH, 80)
                        )
                ),
                new StudentResponse(
                        UUID.randomUUID(),
                        "Tosin",
                        List.of(
                                new SubjectScoreDTO(Subject.MATH, 70),
                                new SubjectScoreDTO(Subject.ICT, 60)
                        )
                )
        };

        when(studentClient.getAllStudents()).thenReturn(mockStudents);

        String url = "http://localhost:" + port + "/api/reports";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Azeez");
        assertThat(response.getBody()).contains("Tosin");
    }



}
