package com.example.HRportal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// here address is the ownerside which owns the control of the relationship with
// the help of employee field
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "add_id")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long add_id;
    private String street;
    private String city;
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "employee_id") // employee_id will be the foreign key of address table
    @JsonManagedReference
    private Employee employee;


}
