package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This service handles all logic related to Employee
 */
@Transactional
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployee(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employeeIn) {
        return employeeRepository.save(employeeIn);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        List<Employee> listEmployee = employeeRepository.getAllByDaysAvailableContains(date.getDayOfWeek());
        List<Employee> results = new ArrayList<>();
        for (Employee currEmployee : listEmployee) {
            if (currEmployee.getSkills().containsAll(skills)) {
                results.add(currEmployee);
            }
        }
        return results;
    }

    public EmployeeDTO ConvertEmployeeToDto(Employee employeeIn) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeIn, employeeDTO);
        employeeDTO.setDaysAvailable(employeeIn.getDaysAvailable());
        employeeDTO.setSkills(employeeIn.getSkills());
        return employeeDTO;
    }

    public Employee ConvertDtoToEmployee(EmployeeDTO employeeDTOIn) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTOIn, employee);
        employee.setDaysAvailable(employeeDTOIn.getDaysAvailable());
        employee.setSkills(employeeDTOIn.getSkills());
        return employee;
    }
}
