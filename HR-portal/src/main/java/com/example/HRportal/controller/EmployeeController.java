package com.example.HRportal.controller;

import com.example.HRportal.domain.EmployeeDTO;
import com.example.HRportal.domain.EmployeeWithAddressDTO;
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
    //Get employee by Name
    @GetMapping("/employeeName") // we can't use pathvariable in this get method.
    //because it can create ambiguity
    public EmployeeDTO getEmployeeByName(@RequestParam String name){
        return employeeService.getEmployeeByName(name);
    }

    @GetMapping("/employee")
    public EmployeeDTO getEmployeeByNameAndDepartment(@RequestParam String name, String department){
        return employeeService.getUniqueEmployee(name, department);
    }

    //Get employee with address
    @GetMapping("/with-addresses")
    public List<EmployeeWithAddressDTO> getAllEmployeesWithAddresses() {
        return employeeService.getAllEmployeesWithAddresses();
    }

    //Create employee
    @PostMapping("/createEmployee")
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
