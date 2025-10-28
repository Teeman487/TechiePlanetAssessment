package akinade.domain.service.impl;



import akinade.domain.dto.EmployeeRequest;
import akinade.domain.dto.EmployeeResponse;
import akinade.domain.entity.Department;
import akinade.domain.entity.Employee;
import akinade.domain.repository.DepartmentRepository;
import akinade.domain.repository.EmployeeRepository;
import akinade.domain.service.EmployeeService;
import akinade.web.exception.DuplicateResourceException;
import akinade.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final EmployeeRepository employeeRepository;
    final DepartmentRepository departmentRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) { // Admin create Employee to Department
        // Verify Department exists
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));


        // if employee with same email already exists in that department
        employeeRepository.findByEmailAndDepartmentId(request.getEmail(), department.getId()).ifPresent(
                existingEmployee -> { throw new DuplicateResourceException(
                        "Employee with email " + request.getEmail() + "already exists in department "+ department.getName());
                });

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setStatus(request.getStatus());
        employee.setDepartment(department);
        employee.setTimeAt(LocalDateTime.now());

      Employee saved = employeeRepository.save(employee);
      // Publish an event to notify Notification
        return mapEmployeeToResponse(saved);
    }


    @Override //
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Department department = null;

        // Check Employee Exists
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        // Validate Department
              department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));

        Optional<Employee> duplicate = employeeRepository.findByEmailAndDepartmentId(request.getEmail(), request.getDepartmentId());
        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            throw new DuplicateResourceException("Another employee in this department already uses this email.");
        }
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setStatus(request.getStatus());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);
        employee.setTimeAt(LocalDateTime.now());
        employeeRepository.save(employee);
        return mapEmployeeToResponse(employee);
    }


    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::mapEmployeeToResponse).toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::mapEmployeeToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(Long departmentId) {
       List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
       if(employees.isEmpty()) throw new ResourceNotFoundException("No employees found for department ID "+ departmentId);

       return employees.stream().map(this::mapEmployeeToResponse).toList();
    }


    private EmployeeResponse mapEmployeeToResponse(Employee e) {
        return new EmployeeResponse(e.getId(), e.getFirstName(), e.getLastName()
        , e.getEmail(), e.getDepartment().getName(), e.getStatus());
    }
}
