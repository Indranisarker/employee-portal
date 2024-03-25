package com.example.HRportal.controller;

import com.example.HRportal.domain.EmployeeDTO;
import com.example.HRportal.entity.Employee;
import com.example.HRportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Get all employees
    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees(){
        return employeeService.getAllEmployee();
    }
    //Get employee by id
    @GetMapping("/employeeId/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getOneEmployee(id);
    }
    //Create employee
    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDto){
        return employeeService.saveEmployees(employeeDto);
    }
    //Update employee
    @PutMapping("/updateEmployee/{id}")
    public EmployeeDTO update(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }
    //Delete employee
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
    }
}
