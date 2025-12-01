package akinade.service;

import akinade.domain.dto.StudentRequest;
import akinade.domain.dto.StudentResponse;
import akinade.domain.dto.SubjectScoreDTO;

import akinade.domain.repository.StudentRepository;
import akinade.domain.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    private StudentRepository studentRepository;
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    void createStudent_ShouldSaveCorrectData() {
        // Given
        Map<akinade.domain.model.Subject, Integer> scores = Map.of(
                akinade.domain.model.Subject.ENGLISH, 90,
                akinade.domain.model.Subject.MATH, 85
        );

        StudentRequest request = new StudentRequest("Azeez", scores);

        akinade.domain.model.Student savedStudent = new akinade.domain.model.Student();
        savedStudent.setId(UUID.randomUUID());
        savedStudent.setName("Azeez");

        savedStudent.setScores(List.of(
                new akinade.domain.model.SubjectScore(UUID.randomUUID(), savedStudent, akinade.domain.model.Subject.ENGLISH, 90),
                new akinade.domain.model.SubjectScore(UUID.randomUUID(), savedStudent, akinade.domain.model.Subject.MATH, 85)
        ));

        when(studentRepository.save(any(akinade.domain.model.Student.class))).thenReturn(savedStudent);

        // When
        StudentResponse response = studentService.createStudent(request);

        // Then
        assertThat(response.name()).isEqualTo("Azeez");
        assertThat(response.scores()).hasSize(2);

        // Assert ENGLISH score
        assertThat(response.scores().stream()
                .filter(s -> s.subject() == akinade.domain.model.Subject.ENGLISH)
                .findFirst().orElseThrow().score())
                .isEqualTo(90);

        // Assert MATH score
        assertThat(response.scores().stream()
                .filter(s -> s.subject() == akinade.domain.model.Subject.MATH)
                .findFirst().orElseThrow().score())
                .isEqualTo(85);

        verify(studentRepository).save(any(akinade.domain.model.Student.class));
    }

    @Test
    void getStudent_ShouldReturnDataWhenExists() {
        // given
        UUID id = UUID.randomUUID();

        akinade.domain.model.Student student = new akinade.domain.model.Student();
        student.setId(id);
        student.setName("Azeez");

        akinade.domain.model.SubjectScore sc = new akinade.domain.model.SubjectScore();
        sc.setSubject(akinade.domain.model.Subject.MATH);
        sc.setScore(95);
        sc.setStudent(student);

        student.getScores().add(sc);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        // when
        StudentResponse response = studentService.getStudent(id);

        // then
        assertThat(response.name()).isEqualTo("Azeez");

        SubjectScoreDTO mathScore = response.scores().stream()
                .filter(s -> s.subject() == akinade.domain.model.Subject.MATH)
                .findFirst()
                .orElseThrow();

        assertThat(mathScore.score()).isEqualTo(95);
    }

    @Test
    void getStudent_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudent(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Could not find student with id");
    }
}
