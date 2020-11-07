package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customerIn, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        // TODO Get list of pets and assign to customer

        return customerRepository.save(customerIn);
    }
}
