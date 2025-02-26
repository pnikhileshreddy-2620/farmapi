// CustomerServiceImpl.java
package com.farmweb.api.serviceImpl;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.model.Customer;
import com.farmweb.api.repository.CustomerRepository;
import com.farmweb.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; // Inject the PasswordEncoder

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerPhone(customerDTO.getCustomerPhone());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());
        customer.setCustomerPassword(passwordEncoder.encode(customerDTO.getCustomerPassword())); // Encode the password!
        customerRepository.save(customer);
    }

    @Override
    public String login(String customerName, String customerPassword) {
        Customer customer = customerRepository.findByCustomerName(customerName);

        if (customer != null && passwordEncoder.matches(customerPassword, customer.getCustomerPassword())) {
            return "Customer found!"; // Authentication successful!
        }
        return "Invalid username or password"; // Authentication failed
    }

    @Override
    public CustomerDTO getCustomerDTOByID(Integer customerId) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer not found with ID: " + customerId));
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerEmail(customer.getCustomerEmail());
        customerDTO.setCustomerPhone(customer.getCustomerPhone());
        customerDTO.setCustomerAddress(customer.getCustomerAddress());
        customerDTO.setCustomerId(customer.getCustomerId());

        return customerDTO;
    }
}