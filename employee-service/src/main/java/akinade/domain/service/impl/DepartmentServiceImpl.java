package akinade.domain.service.impl;

import akinade.domain.dto.DepartmentRequest;
import akinade.domain.dto.DepartmentResponse;
import akinade.domain.entity.Department;
import akinade.domain.entity.Employee;
import akinade.domain.repository.DepartmentRepository;
import akinade.domain.repository.EmployeeRepository;
import akinade.domain.service.DepartmentService;
import akinade.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

     final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Department already exists with this name");
        }
        Department department = new Department(request.getName(), request.getDescription(), LocalDateTime.now());
        Department saved = departmentRepository.save(department);
        return mapDepartmentToResponse(saved);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setTimeAt(LocalDateTime.now());
        Department updated = departmentRepository.save(department);
        return mapDepartmentToResponse(updated);
    }

    @Override
    public void deleteDepartment(Long departmentId) { // deletion of department should persist to employee
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

      Department unassigned =  departmentRepository.findByName("UNASSIGNED_DEPARTMENT_NAME").orElseGet(
                () -> {
                    Department d = new Department();
                    d.setName("UNASSIGNED_DEPARTMENT_NAME");
                    d.setDescription("Default department for unassigned employee");
                   return departmentRepository.save(d); });

       List<Employee> employees = employeeRepository.findByDepartmentId(departmentId); // many to one
       employees.forEach(e -> e.setDepartment(unassigned));  // [] to the departmentId we want to delete

        employeeRepository.saveAll(employees); // load all
        departmentRepository.delete(department);  // later delete the department
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::mapDepartmentToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        return mapDepartmentToResponse(department);
    }

    private DepartmentResponse mapDepartmentToResponse(Department department) {
        return new DepartmentResponse(department.getId(), department.getName(), department.getDescription(), department.getTimeAt());
    }
}
