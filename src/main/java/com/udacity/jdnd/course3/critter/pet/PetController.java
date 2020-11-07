package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.BeanUtils;
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
        return this.ConvertPetToDto(petService.savePet(this.ConvertDtoToPet(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> listDTO = new ArrayList<>();
        for(Pet currPet : petService.getPets()) {
            listDTO.add(this.ConvertPetToDto(currPet));
        }
        return listDTO;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }

    private static PetDTO ConvertPetToDto(Pet petIn) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petIn, petDTO);
        return petDTO;
    }

    private static Pet ConvertDtoToPet(PetDTO petDtoIn) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDtoIn, pet);
        return pet;
    }
}
