package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet petIn) {
        return petRepository.save(petIn);
    }

}
