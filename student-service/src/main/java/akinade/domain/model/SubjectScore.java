package akinade.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="subject_scores")
public class SubjectScore {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name ="student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    private int score;

    public SubjectScore() {
    }

    public SubjectScore(UUID id, Student student, Subject subject, int score) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.score = score;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
