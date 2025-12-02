package akinade.web.controllers;

import akinade.AbstractIT;
import akinade.domain.dto.StudentRequest;
import akinade.domain.model.Subject;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StudentIntegrationTest extends AbstractIT {

    @Test
    void testGetStudentById() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/students/{id}", savedStudent.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo("Azeez"))
                .body("scores.subject", hasItems("MATH", "ICT"))
                .body("scores.score", hasItems(90, 90));
    }

    @Test
    void testCreateStudentScores() {
        var scores = Map.of(Subject.ENGLISH, 95);
        var request = new StudentRequest("Bola", scores);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/students")
                .then()
                .statusCode(200)
                .body("name", equalTo("Bola"))
                .body("scores.find { it.subject == 'ENGLISH' }.score", equalTo(95));
    }

}
