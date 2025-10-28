package akinade.domain.service;

import akinade.domain.dto.EmployeeRequest;
import akinade.domain.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

   EmployeeResponse createEmployee(EmployeeRequest employee);
   EmployeeResponse  updateEmployee(Long id, EmployeeRequest employee);
   void  deleteEmployee(Long id);
   List<EmployeeResponse> getAllEmployees();
   EmployeeResponse getEmployeeById(Long id);
   List<EmployeeResponse> getEmployeesByDepartment(Long departmentId);

}
