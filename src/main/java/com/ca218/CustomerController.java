package com.ca218;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //requests
    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable int id){
        customerService.deleteCustomer(id);
    }
    @PostMapping
    public void createCustomer(@RequestBody Customer customer){
        customerService.insertCustomer(customer);
    }
    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable int id ,
                                   @RequestBody Customer customer){
        customer.setId(id);
        customerService.updateCustomer(customer);
    }
}
