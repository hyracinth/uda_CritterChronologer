package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent())
            return customer.get();
        else
            return null;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customerIn, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        // TODO Get list of pets and assign to customer

        return customerRepository.save(customerIn);
    }
}
