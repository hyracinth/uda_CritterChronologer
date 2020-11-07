package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return petService.ConvertPetToDto(
                petService.savePet(petService.ConvertDtoToPet(petDTO), petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petService.ConvertPetToDto(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> listDTO = new ArrayList<>();
        for(Pet currPet : petService.getPets()) {
            listDTO.add(petService.ConvertPetToDto(currPet));
        }
        return listDTO;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> listDTO = new ArrayList<>();
        for(Pet currPet : petService.getPetByCustomerId(ownerId)) {
            listDTO.add(petService.ConvertPetToDto(currPet));
        }
        return listDTO;
    }
}
