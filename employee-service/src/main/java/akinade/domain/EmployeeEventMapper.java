package akinade.domain;

import akinade.domain.dto.EmployeeCancelledEvent;
import akinade.domain.dto.EmployeeCreatedEvent;

import akinade.domain.dto.EmployeeErrorEvent;
import akinade.domain.entity.Employee;

import java.time.LocalDateTime;
import java.util.UUID;



public class EmployeeEventMapper {

   public static EmployeeCreatedEvent buildEmployeeCreatedEvent(Employee employee) {
        return new EmployeeCreatedEvent(
                UUID.randomUUID().toString(),
                employee.getEmployeeCode(),
                LocalDateTime.now());
    }

    public static EmployeeCancelledEvent buildEmployeeCancelledEvent(Employee employee, String reason) {
        return new EmployeeCancelledEvent(
                UUID.randomUUID().toString(),
                employee.getEmployeeCode(),
                reason,
                LocalDateTime.now());
    }
    public static EmployeeErrorEvent buildEmployeeErrorEvent(Employee employee, String reason) {
        return new EmployeeErrorEvent(
                UUID.randomUUID().toString(),
                employee.getEmployeeCode(),
                reason,
                LocalDateTime.now());
    }


}
