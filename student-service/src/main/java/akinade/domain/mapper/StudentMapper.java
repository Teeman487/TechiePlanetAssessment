package akinade.domain.mapper;

import akinade.domain.dto.StudentResponse;
import akinade.domain.dto.SubjectScoreDTO;
import akinade.domain.model.Student;

import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentResponse toResponse(Student student) {
        return new StudentResponse(
               student.getId(),
               student.getName(),
               student.getScores()
                       .stream()
                       .map(s -> new SubjectScoreDTO(s.getSubject(), s.getScore()))
                       .collect(Collectors.toList())
        );

    }
}
