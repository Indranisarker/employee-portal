package com.example.HRportal.controller;

import com.example.HRportal.domain.EmployeeDTO;
import com.example.HRportal.domain.EmployeeWithAddressDTO;
import com.example.HRportal.entity.Employee;
import com.example.HRportal.exception.ResourceNotFound;
import com.example.HRportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Get all employees
//    @GetMapping("/employees")
//    public List<EmployeeDTO> getEmployees(@RequestParam int pageNo, int pageSize,  String sortOrder){
//        return employeeService.getAllEmployee(pageNo, pageSize, sortOrder);
//    }
    private static final int MIN_PAGE_SIZE = 5;
    private static final int MAX_PAGE_SIZE = 50;
    @GetMapping("/employees")
    //Also we can set default values for these parameters,
    // these default values work when we didn't give any parameters from frontend
    public Page<Employee> getEmployees(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                       @RequestParam(defaultValue = "asc") String sortOrder){
        //pageSize can be limited in min and max value
        int validPageSize = Math.max(MIN_PAGE_SIZE, Math.min(pageSize, MAX_PAGE_SIZE));
        return employeeService.getAllEmployee(pageNo, validPageSize, sortOrder);
    }

    //Get employee by id
    @GetMapping("/employeeId/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long id) {
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
