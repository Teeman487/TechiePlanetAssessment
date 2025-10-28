package akinade.domain.service;

import akinade.domain.dto.DepartmentRequest;
import akinade.domain.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest request);

    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);

    void deleteDepartment(Long id);

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);
}
