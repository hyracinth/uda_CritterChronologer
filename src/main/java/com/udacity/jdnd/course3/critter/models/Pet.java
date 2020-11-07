package com.udacity.jdnd.course3.critter.models;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue
    private long id;

    private PetType type;

    private String name;

    private long ownerId;

    private LocalDate birthDate;

    private String notes;
}
