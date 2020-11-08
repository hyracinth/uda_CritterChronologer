package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.models.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.ConvertScheduleToDto(
                scheduleService.createSchedule(scheduleService.ConvertDtoToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> listDtos = new ArrayList<>();
        for(Schedule currSchedule : scheduleService.getSchedules()) {
            listDtos.add(scheduleService.ConvertScheduleToDto(currSchedule));
        }
        return listDtos;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> listDtos = new ArrayList<>();
        for(Schedule currSchedule : scheduleService.getScheduleForPet(petId)) {
            listDtos.add(scheduleService.ConvertScheduleToDto(currSchedule));
        }
        return listDtos;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> listDtos = new ArrayList<>();
        for(Schedule currSchedule : scheduleService.getScheduleForEmployee(employeeId)) {
            listDtos.add(scheduleService.ConvertScheduleToDto(currSchedule));
        }
        return listDtos;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
}
