package com.farmweb.api.controller;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.model.Customer;
import com.farmweb.api.model.History;
import com.farmweb.api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
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
// working
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Integer customerId) {
        try {
            CustomerDTO customer = customerService.deleteCustomerDTOByID(customerId);
            return ResponseEntity.ok(Map.of("message", "Customer deleted successfully", "customerId", customerId.toString()));
        } catch (CustomerException customerException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "Customer Not Found", "message", customerException.getMessage())
            );
        }
    }

    @PutMapping("/{customerID}/update-name")
    public ResponseEntity<Object> updateCustomerName(
            @PathVariable Integer customerID,
            @RequestBody Map<String, String> requestBody) {

        String newCustomerName = requestBody.get("customerName");
        if (newCustomerName == null || newCustomerName.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Customer name cannot be empty"));
        }

        try {
            CustomerDTO updatedCustomer = customerService.updateCustomerName(customerID, newCustomerName);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{customerID}/update-email")
    public ResponseEntity<CustomerDTO> updateCustomerEmail(
            @PathVariable Integer customerID,
            @RequestBody Map<String, String> requestBody) {

        String newCustomerEmail = requestBody.get("customerEmail");

        if (newCustomerEmail == null || newCustomerEmail.isEmpty()) {
            return ResponseEntity.badRequest().body(new CustomerDTO("Error: Email cannot be empty"));
        }

        try {
            CustomerDTO updatedCustomer = customerService.updateCustomerEmail(customerID, newCustomerEmail);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerDTO(e.getMessage()));
        }
    }


    @PutMapping("/{customerID}/update-phone")
    public ResponseEntity<CustomerDTO> updateCustomerPhone(
            @PathVariable Integer customerID,
            @RequestBody Map<String, String> requestBody) {

        String newCustomerPhone = requestBody.get("customerPhone");

        if (newCustomerPhone == null || newCustomerPhone.isEmpty()) {
            return ResponseEntity.badRequest().body(new CustomerDTO("Error: Phone number cannot be empty"));
        }

        try {
            CustomerDTO updatedCustomer = customerService.updateCustomerPhoneNumber(customerID, newCustomerPhone);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerDTO(e.getMessage()));
        }
    }

    @PutMapping("/{customerID}/update-address")
    public ResponseEntity<CustomerDTO> updateCustomerAddress(
            @PathVariable Integer customerID,
            @RequestBody Map<String, String> requestBody) {

        String newCustomerAddress = requestBody.get("customerAddress");

        if (newCustomerAddress == null || newCustomerAddress.isEmpty()) {
            return ResponseEntity.badRequest().body(new CustomerDTO("Error: Address cannot be empty"));
        }

        try {
            CustomerDTO updatedCustomer = customerService.updateCustomerAddress(customerID, newCustomerAddress);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerDTO(e.getMessage()));
        }
    }
    @GetMapping("/{customerId}/last10transactions")
    public ResponseEntity<List<History>> getLast10Transactions(@PathVariable Integer customerId) {
        List<History> transactions = customerService.getLast10TransactionsByCustomerId(customerId);
        return ResponseEntity.ok(transactions);
    }
}



