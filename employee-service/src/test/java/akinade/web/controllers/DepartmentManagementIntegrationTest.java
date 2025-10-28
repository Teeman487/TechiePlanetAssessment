package akinade.web.controllers;

import akinade.AbstractIT;
import akinade.domain.dto.DepartmentRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentManagementIntegrationTest extends AbstractIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenCreateDepartment_thenReturn200() {
        DepartmentRequest req = new DepartmentRequest();
        req.setName("Sales and Marketing");
        req.setDescription("Handles all technical tasks");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + RestAssured.port + "/api/admin/departments",
                req,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldGetAllDepartments() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/admin/departments")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    void shouldGetDepartmentById() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/admin/departments/{departmentId}", 1L)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue());
    }

    @Test
    void shouldUpdateDepartment() {
        DepartmentRequest req = new DepartmentRequest();
        req.setName("Finance Updated");
        req.setDescription("Updated department");

        given().contentType(ContentType.JSON)
                .body(req)
                .when()
                .put("/api/admin/departments/{departmentId}", 2)
                .then()
                .statusCode(200)
                .body("name", equalTo("Finance Updated"));
    }

    @Test
    void shouldDeleteDepartment() {
        given().contentType(ContentType.JSON)
                .when()
                .delete("/api/admin/departments/{departmentId}", 1L)
                .then()
                .statusCode(204);
    }

//    @Test
//    void shouldReturnNotFoundForInvalidDepartment() {
//        long invalidId = 999L;
//        given().contentType(ContentType.JSON)
//                .when()
//                .get("/api/admin/departments/{departmentId}", invalidId)
//                .then()
//                .statusCode(404)
//                .body("status", equalTo(404))
//                .body("title", containsString("Department not found"));
//    }
}
