package com.farmweb.api.controller;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.model.Customer;
import com.farmweb.api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<String> createCustomer(@Valid  @RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return ResponseEntity.ok("Customer created successfully");
    }
    @PostMapping("/login")
    public String login(@RequestBody CustomerDTO customerDTO) {
        return customerService.login(customerDTO.getCustomerName(), customerDTO.getCustomerPassword());
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer customerId) {
        CustomerDTO customer = customerService.getCustomerDTOByID(customerId);
        return ResponseEntity.ok(customer);
    }


}
