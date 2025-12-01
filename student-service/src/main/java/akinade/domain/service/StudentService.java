package akinade.domain.service;

import akinade.domain.dto.StudentRequest;
import akinade.domain.dto.StudentResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);
    StudentResponse getStudent(UUID id);
    List<StudentResponse> getAllStudents();
}
