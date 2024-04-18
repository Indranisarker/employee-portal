package com.example.HRportal.controller;

import com.example.HRportal.entity.Address;
import com.example.HRportal.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/address/{id}")
    public Optional<Address> getAddress(@PathVariable Long id){
        return addressRepository.findById(id);
    }
    @GetMapping("/address")
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

}
