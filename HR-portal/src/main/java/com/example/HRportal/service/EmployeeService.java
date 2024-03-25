package com.example.HRportal.service;

import com.example.HRportal.domain.EmployeeDTO;
import com.example.HRportal.entity.Employee;
import com.example.HRportal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> allEmployees = new ArrayList<>();
        for(Employee employee:employees){
            allEmployees.add(EmployeeDTO.toDto(employee));
        }
        return allEmployees;
    }
    public EmployeeDTO getOneEmployee(Long id){
        Optional<Employee> em = employeeRepository.findById(id);

        Employee employee = em.get();
        if(employee == null){
            throw new RuntimeException("Employee is not found!");
        }
        //Mapping entity to DTO
        return EmployeeDTO.toDto(employee);
    }
    public EmployeeDTO saveEmployees(EmployeeDTO employeeDto){
        //Mapping DTo to entity
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        Employee employees = employeeRepository.save(employee);
        return EmployeeDTO.toDto(employees);
    }
    public EmployeeDTO updateEmployee(Long id, Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employees = null;
        if(employeeOptional.isPresent()){
            Employee em = employeeOptional.get();
            em.setName(employee.getName());
            em.setDepartment(employee.getDepartment());
            em.setSalary(employee.getSalary());
            employees = employeeRepository.save(em);
        }
        return EmployeeDTO.toDto(employees);

    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
