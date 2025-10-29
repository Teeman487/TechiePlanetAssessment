package akinade.domain.dto;

public class EmployeeResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String department;
    Status status;
    String employeeCode;


    public EmployeeResponse(Long id, String firstName, String lastName, String email, String department, Status status, String employeeCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.status = status;
        this.employeeCode = employeeCode;
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

    public Status getStatus() {
        return status;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }
}
