package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This service handles all logic related to Customer
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer getCustomer(Long customerId) {
        return customerRepository.getOne(customerId);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByPetId(Long petId) {
        return petRepository.getOne(petId).getCustomer();
    }

    public Customer saveCustomer(Customer customerIn, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null) {
            for (Long petId : petIds) {
                pets.add(petRepository.getOne(petId));
            }
            customerIn.setPets(pets);
        }
        return customerRepository.save(customerIn);
    }

    public CustomerDTO ConvertCustomerToDto(Customer customerIn) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerIn, customerDTO);

        List<Long> petIds = new ArrayList<>();
        List<Pet> pets = customerIn.getPets();
        if (pets != null) {
            for (Pet currPet : pets) {
                petIds.add(currPet.getId());
            }
        }
        customerDTO.setPetIds(petIds);

        return customerDTO;
    }

    public Customer ConvertDtoToCustomer(CustomerDTO customerDtoIn) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDtoIn, customer);

        List<Pet> pets = new ArrayList<>();
        List<Long> petIds = customerDtoIn.getPetIds();
        if (petIds != null) {
            for (Long petId : petIds) {
                pets.add(petRepository.getOne(petId));
            }
        }
        customer.setPets(pets);

        return customer;
    }
}
