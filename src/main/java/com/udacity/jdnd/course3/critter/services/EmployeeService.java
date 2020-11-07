package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public EmployeeDTO ConvertEmployeeToDto(Employee employeeIn) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeIn, employeeDTO);
        return employeeDTO;
    }

    public Employee ConvertDtoToEmployee(EmployeeDTO employeeDTOIn) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTOIn, employee);
        return employee;
    }
}
