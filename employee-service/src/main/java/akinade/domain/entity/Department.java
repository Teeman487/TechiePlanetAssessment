package akinade.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_generator")
    @SequenceGenerator(name = "department_id_generator", sequenceName = "department_id_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String description;

    private LocalDateTime timeAt;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;


    public Department(String name, String description, LocalDateTime timeAtAt) {
        this.name = name;
        this.description = description;
        this.timeAt = timeAtAt;
    }

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Department(String name) {
        this.name = name;
    }

    public Department() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(LocalDateTime timeAt) {
        this.timeAt = timeAt;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
