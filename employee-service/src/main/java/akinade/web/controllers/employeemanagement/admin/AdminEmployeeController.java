package akinade.web.controllers.employeemanagement.admin;

import akinade.domain.dto.EmployeeRequest;
import akinade.domain.dto.EmployeeResponse;
import akinade.domain.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/employees")
public class AdminEmployeeController {
    private final EmployeeService employeeService;

    public AdminEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long employeeId,
                                                           @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, request));
    }

    @DeleteMapping("/{deleteId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long deleteId) {
        employeeService.deleteEmployee(deleteId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }


}
