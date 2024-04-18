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
// here employee entity is the inverse side
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String department;
    private int salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL) // this employee field control the relationship in Address entity.
    @JsonBackReference
    private Address address;

// We can use joincolumn in both entities because it is one to one relationship
//    @OneToOne
//    @JoinColumn(name = "add_id") // employee_id will be the foreign key of address table
//    private Address address;

}
