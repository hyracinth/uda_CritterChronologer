package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent())
            return employee.get();
        else
            return null;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employeeIn) {
        return employeeRepository.save(employeeIn);
    }
}
