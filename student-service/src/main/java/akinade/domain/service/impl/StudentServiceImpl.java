package akinade.domain.service.impl;

import akinade.domain.StudentNotFoundException;
import akinade.domain.dto.StudentRequest;
import akinade.domain.dto.StudentResponse;
import akinade.domain.mapper.StudentMapper;
import akinade.domain.model.Student;
import akinade.domain.model.SubjectScore;
import akinade.domain.repository.StudentRepository;
import akinade.domain.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.name());

        request.scores().forEach(((subject, score) -> {
            SubjectScore sc = new SubjectScore();
            sc.setScore(score);
            sc.setSubject(subject);
            sc.setStudent(student);
            student.getScores().add(sc);
        }));
      return StudentMapper.toResponse(studentRepository.save(student));
    }


    public StudentResponse getStudent(UUID id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toResponse)
                .orElseThrow( () -> new StudentNotFoundException("Could not find student with id: " + id));
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toResponse)
                .toList();
    }



}
