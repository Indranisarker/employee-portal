package com.example.HRportal.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeWithAddressDTO {
    private String employeeName;
    private String department;
    private int salary;
    private String street;
    private String city;
    private String zipCode;

}
