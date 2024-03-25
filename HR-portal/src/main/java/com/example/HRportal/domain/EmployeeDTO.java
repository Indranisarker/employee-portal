package com.example.HRportal.domain;

import com.example.HRportal.entity.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private String name;
    private String department;
    private int salary;
    public static EmployeeDTO toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setName(employee.getName());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setSalary(employee.getSalary());

        return employeeDto;
    }
}
