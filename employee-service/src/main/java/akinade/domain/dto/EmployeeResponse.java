package akinade.domain.dto;

public class EmployeeResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String department;
    String status;


    public EmployeeResponse(Long id, String firstName, String lastName, String email, String department, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getStatus() {
        return status;
    }
}
