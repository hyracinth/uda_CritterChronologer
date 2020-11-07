package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    public Pet getPet(Long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isPresent())
            return pet.get();
        else
            return null;
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet petIn) {
        return petRepository.save(petIn);
    }

}
