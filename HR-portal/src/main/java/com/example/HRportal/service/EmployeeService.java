package com.example.HRportal.service;

import com.example.HRportal.domain.EmployeeDTO;
import com.example.HRportal.domain.EmployeeWithAddressDTO;
import com.example.HRportal.entity.Address;
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
        Employee employee;
        if(em.isPresent()){
          employee = em.get();
        }
       else{
            throw new RuntimeException("Employee is not found for id: " + id);
        }
        //Mapping entity to DTO
        return EmployeeDTO.toDto(employee);
    }

    public EmployeeDTO getEmployeeByName(String name) {
        Optional<Employee> em = employeeRepository.findByName(name);
        Employee employee;
        if(em.isPresent()){
            employee = em.get();
        }
        else{
            throw new RuntimeException("Employee is not found for the name: " + name);
        }
        //Mapping entity to DTO
        return EmployeeDTO.toDto(employee);
    }
    public EmployeeDTO getUniqueEmployee(String name, String department) {
        Optional<Employee> em = employeeRepository.findByNameAndAndDepartment(name, department);
        Employee employee;
        if(em.isPresent()){
            employee = em.get();
        }
        else{
            throw new RuntimeException("Employee is not found!");
        }
        //Mapping entity to DTO
        return EmployeeDTO.toDto(employee);
    }

    public EmployeeDTO saveEmployees(EmployeeDTO employeeDto){
        //error handling
        if(employeeDto.getName() == null)throw new RuntimeException("Employee Name must be Provided!");
        if(employeeDto.getDepartment() == null)throw new RuntimeException("Employee Department must be Provided!");
        if(employeeDto.getSalary() == 0)throw new RuntimeException("Employee Salary must be Provided!");

        //Mapping DTo to entity
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        Employee employees = employeeRepository.save(employee);

        return EmployeeDTO.toDto(employees);
    }
    public EmployeeDTO updateEmployee(Long id, Employee employee){
        //Defensive code means handle all negative case which doesn't give expected output of the application
        //this type of error handling is called refined error handling means specify each kind of error
        if(employee == null){
            throw new RuntimeException("Employee Details not found");
        }
        if(employee.getName() == null) throw new RuntimeException("Employee Name is not found");
        if(employee.getSalary() == 0) throw new RuntimeException("Employee Salary is not found");
        if(employee.getDepartment() == null) throw new RuntimeException("Employee Department is not found");

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

    public void delete(Long id) {
        Optional<Employee> em = employeeRepository.findById(id);
        Employee employee;
        if (em.isPresent()) {
            employee = em.get();
            employeeRepository.delete(employee);
            System.out.printf("Delete Successfully!!");

        } else {
            throw new RuntimeException("Employee with this " + id + " is not present");
        }
    }
        // Get Employee with address
        public List<EmployeeWithAddressDTO> getAllEmployeesWithAddresses() {
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeWithAddressDTO> employeeDTOs = new ArrayList<>();

            for (Employee employee : employees) {
                Address address = employee.getAddress();
                EmployeeWithAddressDTO employeeDTO = new EmployeeWithAddressDTO();
                employeeDTO.setEmployeeName(employee.getName());
                employeeDTO.setDepartment(employee.getDepartment());
                employeeDTO.setSalary(employee.getSalary());
                if (address != null) {
                    employeeDTO.setStreet(address.getStreet());
                    employeeDTO.setCity(address.getCity());
                    employeeDTO.setZipCode(address.getZipCode());
                }
                employeeDTOs.add(employeeDTO);
            }

            return employeeDTOs;
        }
    }

