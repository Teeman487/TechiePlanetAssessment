package akinade.domain.service;


import akinade.domain.dto.EmployeeRequest;
import akinade.domain.dto.EmployeeResponse;
import akinade.domain.dto.Status;
import akinade.domain.entity.Department;
import akinade.domain.entity.Employee;
import akinade.domain.repository.DepartmentRepository;
import akinade.domain.repository.EmployeeRepository;
import akinade.domain.service.impl.EmployeeEventService;
import akinade.domain.service.impl.EmployeeServiceImpl;
import akinade.web.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeEventService employeeEventService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department("HR", "Handles HR tasks");
        department.setId(1L);

        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("toheeb@corp.com");
        employee.setDepartment(department);
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Toheeb");
        request.setLastName("Akande");
        request.setEmail("toheeb@corp.com");
        request.setStatus(Status.ACTIVE);
        request.setDepartmentId(1L);

        Department department = new Department();
        department.setId(1L);
        department.setName("Engineering");

        Employee employee = new Employee();
        employee.setId(100L);
        employee.setEmployeeCode("D44");
        employee.setFirstName("Toheeb");
        employee.setLastName("Akande");
        employee.setEmail("toheeb@corp.com");
        employee.setStatus(Status.ACTIVE);
        employee.setDepartment(department);

        // Mock repository behavior
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        EmployeeResponse response = employeeService.createEmployee(request);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo("toheeb@corp.com");
        assertThat(response.getDepartment()).isEqualTo("Engineering");
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(departmentRepository, times(1)).findById(1L);
    }


    @Test
    void testUpdateEmployee() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Engineering");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeRequest update = new EmployeeRequest();
        update.setFirstName("Jane");
        update.setLastName("Smith");
        update.setEmail("jane@corp.com");
        update.setDepartmentId(1L);

        EmployeeResponse response = employeeService.updateEmployee(1L, update);

        assertThat(response.getFirstName()).isEqualTo("Jane");
        verify(employeeRepository).save(any(Employee.class));
    }


    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(1L);
        verify(employeeRepository).delete(employee);
    }

    @Test
    void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        EmployeeResponse response = employeeService.getEmployeeById(1L);
        assertThat(response.getEmail()).isEqualTo("toheeb@corp.com");
    }

    @Test
    void testGetEmployeesByDepartment() {
        when(employeeRepository.findByDepartmentId(1L)).thenReturn(List.of(employee));
        List<EmployeeResponse> employees = employeeService.getEmployeesByDepartment(1L);
        assertThat(employees).hasSize(1);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> employeeService.getEmployeeById(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

}
