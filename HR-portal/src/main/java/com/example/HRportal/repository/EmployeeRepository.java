package com.example.HRportal.repository;

import com.example.HRportal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByName(String name);

    Optional<Employee> findByNameAndAndDepartment(String name, String department);
}
