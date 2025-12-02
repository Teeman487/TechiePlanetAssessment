package akinade;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import akinade.domain.model.Student;
import akinade.domain.model.Subject;
import akinade.domain.model.SubjectScore;
import akinade.domain.repository.StudentRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
public abstract class AbstractIT {
    @LocalServerPort
    int port;

    @Autowired
   protected StudentRepository studentRepository;

   protected Student savedStudent;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        studentRepository.deleteAll(); // clean DB

        // and then...
        Student student = new Student();
        student.setName("Azeez");
        student.setScores(List.of(
                new SubjectScore(null,student, Subject.MATH,  90),
                new SubjectScore(null,student, Subject.ICT,  90)
        ));

        savedStudent = studentRepository.save(student);
    }


}
