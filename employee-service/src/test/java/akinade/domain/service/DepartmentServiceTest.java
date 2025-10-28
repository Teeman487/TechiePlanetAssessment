package akinade.domain.service;


import akinade.domain.dto.DepartmentRequest;
import akinade.domain.dto.DepartmentResponse;
import akinade.domain.entity.Department;
import akinade.domain.repository.DepartmentRepository;
import akinade.domain.repository.EmployeeRepository;
import akinade.domain.service.impl.DepartmentServiceImpl;
import akinade.web.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department("Finance", "Handles company funds");
        department.setId(1L);
    }

    @Test
    void testCreateDepartment() {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("Finance");
        request.setDescription("Handles company funds");

        when(departmentRepository.existsByName("Finance")).thenReturn(false);
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentResponse response = departmentService.createDepartment(request);

        assertThat(response.getName()).isEqualTo("Finance");
        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    void testUpdateDepartment() {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("Accounts");
        request.setDescription("Updated");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentResponse response = departmentService.updateDepartment(1L, request);
        assertThat(response.getName()).isEqualTo("Accounts");
    }

    @Test
    void testDeleteDepartment() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(employeeRepository.findByDepartmentId(1L)).thenReturn(Collections.emptyList());

        departmentService.deleteDepartment(1L);

        verify(departmentRepository).delete(department);
    }

    @Test
    void testGetAllDepartments() {
        when(departmentRepository.findAll()).thenReturn(List.of(department));
        List<DepartmentResponse> responses = departmentService.getAllDepartments();
        assertThat(responses).hasSize(1);
    }

    @Test
    void testGetDepartmentById() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        DepartmentResponse response = departmentService.getDepartmentById(1L);
        assertThat(response.getName()).isEqualTo("Finance");
    }

    @Test
    void testGetDepartmentById_NotFound() {
        when(departmentRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> departmentService.getDepartmentById(999L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
