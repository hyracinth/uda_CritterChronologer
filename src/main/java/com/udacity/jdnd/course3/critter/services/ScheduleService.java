package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.models.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule createSchedule(Schedule scheduleIn) {
        return scheduleRepository.save(scheduleIn);
    }

    public ScheduleDTO ConvertScheduleToDto(Schedule scheduleIn) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleIn, scheduleDTO);

        if(scheduleIn.getEmployees() != null) {
            List<Long> listIds = new ArrayList<>();
            for(Employee currEmployee : scheduleIn.getEmployees()) {
                listIds.add(currEmployee.getId());
            }
            scheduleDTO.setEmployeeIds(listIds);
        }

        if(scheduleIn.getPets() != null) {
            List<Long> listIds = new ArrayList<>();
            for(Pet currPet : scheduleIn.getPets()) {
                listIds.add(currPet.getId());
            }
            scheduleDTO.setPetIds(listIds);
        }

        return scheduleDTO;
    }

    public Schedule ConvertDtoToSchedule(ScheduleDTO scheduleDTOIn) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTOIn, schedule);

        if(scheduleDTOIn.getEmployeeIds() != null) {
            List<Employee> listEmployees = new ArrayList<>();
            for(Long currEmployeeId : scheduleDTOIn.getEmployeeIds()) {
                listEmployees.add(employeeRepository.getOne(currEmployeeId));
            }
            schedule.setEmployees(listEmployees);
        }

        if(scheduleDTOIn.getPetIds() != null) {
            List<Pet> listPets = new ArrayList<>();
            for(Long currPetId : scheduleDTOIn.getPetIds()) {
                listPets.add(petRepository.getOne(currPetId));
            }
            schedule.setPets(listPets);
        }

        return schedule;
    }
}
