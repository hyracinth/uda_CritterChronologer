package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service handles all logic related to Pet
 */
@Transactional
@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet getPet(Long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet petIn, Long ownerId) {
        Customer owner = customerRepository.getOne(ownerId);
        petIn.setCustomer(owner);
        Pet savedPet = petRepository.save(petIn);
        owner.insertPet(savedPet);
        customerRepository.save(owner);
        return savedPet;
    }

    public List<Pet> getPetByCustomerId(Long customerId) {
        return petRepository.getAllByCustomerId(customerId);
    }

    public PetDTO ConvertPetToDto(Pet petIn) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petIn, petDTO);
        petDTO.setOwnerId(petIn.getCustomer().getId());
        return petDTO;
    }

    public Pet ConvertDtoToPet(PetDTO petDtoIn) {
        Pet pet = new Pet();
        Customer owner = customerRepository.getOne(petDtoIn.getOwnerId());
        pet.setCustomer(owner);
        BeanUtils.copyProperties(petDtoIn, pet);
        return pet;
    }
}
