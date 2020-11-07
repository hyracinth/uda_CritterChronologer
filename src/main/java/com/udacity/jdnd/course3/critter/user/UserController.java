package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return this.ConvertCustomerToDto(customerService.saveCustomer(this.ConvertDtoToCustomer(customerDTO), null));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> listDTO = new ArrayList<>();
        for(Customer currCustomer : customerService.getCustomers()) {
            listDTO.add(this.ConvertCustomerToDto(currCustomer));
        }
        return listDTO;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return this.ConvertEmployeeToDto(employeeService.saveEmployee(this.ConvertDtoToEmployee(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private static CustomerDTO ConvertCustomerToDto(Customer customerIn) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerIn, customerDTO);
        return customerDTO;
    }

    private static Customer ConvertDtoToCustomer(CustomerDTO customerDtoIn) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDtoIn, customer);
        return customer;
    }

    private static EmployeeDTO ConvertEmployeeToDto(Employee employeeIn) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeIn, employeeDTO);
        return employeeDTO;
    }

    private static Employee ConvertDtoToEmployee(EmployeeDTO employeeDTOIn) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTOIn, employee);
        return employee;
    }

}
