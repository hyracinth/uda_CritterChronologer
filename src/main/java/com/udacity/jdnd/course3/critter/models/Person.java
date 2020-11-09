package com.udacity.jdnd.course3.critter.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is a base entity for to handle both customer and employee
 *  Both Customer and Employee share common fields of id and name
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @Nationalized
    private String name;

}