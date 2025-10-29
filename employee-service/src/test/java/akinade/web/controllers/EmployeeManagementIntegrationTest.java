package akinade.web.controllers;

import akinade.AbstractIT;
import akinade.domain.dto.EmployeeRequest;
import akinade.domain.dto.Status;
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

public class EmployeeManagementIntegrationTest extends AbstractIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenAdminCreateEmployee_thenReturn200() { // ADMIN
        EmployeeRequest req = new EmployeeRequest();
        req.setFirstName("John");
        req.setLastName("Smith");
        req.setEmail("john@corp.com");
        req.setStatus(Status.ACTIVE);
        req.setDepartmentId(1L); // Pre-seeded department in testdata.sql or migration

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + RestAssured.port + "/api/admin/employees",
                req,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldAdminGetAllEmployees() { // ADMIN
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/admin/employees")
                .then()
                .statusCode(200)
                .body("$", not(empty())); // checks we get a non-empty list
    }

    @Test
    void shouldGetEmployeeById() {  // EMPLOYEE
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/employees/{employeeId}", 1L)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("firstName", notNullValue())
                .body("email", notNullValue());
    }

//    @Test
//    void shouldReturnNotFoundForInvalidEmployeeId() {
//        long invalidId = 9999L;
//        given().contentType(ContentType.JSON)
//                .when()
//                .get("/api/employees/{employeeId}", invalidId)
//                .then()
//                .statusCode(404)
//                .body("status", equalTo(404))
//                .body("title", containsString("Employee Not Found"));
//    }

    @Test
    void shouldManagerGetEmployeesByDepartment() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/manager/departments/{departmentId}/employees", 1L)
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

}
