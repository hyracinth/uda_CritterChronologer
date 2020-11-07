package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule createSchedule(Schedule scheduleIn) {
        return scheduleRepository.save(scheduleIn);
    }

    public ScheduleDTO ConvertScheduleToDto(Schedule scheduleIn) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleIn, scheduleDTO);
        return scheduleDTO;
    }

    public Schedule ConvertDtoToSchedule(ScheduleDTO scheduleDTOIn) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTOIn, schedule);
        return schedule;
    }
}
