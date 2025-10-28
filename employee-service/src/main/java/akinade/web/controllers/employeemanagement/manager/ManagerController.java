package akinade.web.controllers.employeemanagement.manager;

import akinade.domain.dto.EmployeeResponse;
import akinade.domain.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manager/departments")
public class ManagerController {
    private final EmployeeService employeeService;

    public ManagerController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeResponse>> viewEmployeesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(departmentId));
    }
}
