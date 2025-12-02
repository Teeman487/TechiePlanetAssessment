package akinade.web.controllers;

import akinade.domain.dto.StudentRequest;
import akinade.domain.dto.StudentResponse;
import akinade.domain.dto.SubjectScoreDTO;
import akinade.domain.model.Subject;
import akinade.domain.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StudentController.class)
class StudentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createStudent_ShouldReturnCreatedStudent() throws Exception {

        StudentRequest request = new StudentRequest(
                "Azeez",
                Map.of(Subject.MATH, 95, Subject.ENGLISH, 90)
        );

        StudentResponse response = new StudentResponse(
                UUID.randomUUID(),
                "Azeez",
                List.of(
                        new SubjectScoreDTO(Subject.MATH, 95),
                        new SubjectScoreDTO(Subject.ENGLISH, 90)
                )
        );

        when(studentService.createStudent(any(StudentRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Azeez"))
                .andExpect(jsonPath("$.scores[0].score").value(95));
    }

    @Test
    void getStudent_ShouldReturnStudentData() throws Exception {

        UUID id = UUID.randomUUID();

        StudentResponse response = new StudentResponse(
                id,
                "Azeez",
                List.of(new SubjectScoreDTO(Subject.MATH, 95))
        );

        when(studentService.getStudent(id)).thenReturn(response);

        mockMvc.perform(get("/api/students/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Azeez"))
                .andExpect(jsonPath("$.scores[0].subject").value("MATH"))
                .andExpect(jsonPath("$.scores[0].score").value(95));
    }
}
