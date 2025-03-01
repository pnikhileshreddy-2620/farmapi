// CustomerServiceImpl.java
package com.farmweb.api.serviceImpl;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.model.Customer;
import com.farmweb.api.model.History;
import com.farmweb.api.repository.CustomerRepository;
import com.farmweb.api.repository.HistoryRepository;
import com.farmweb.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HistoryRepository historyRepository;

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

    @Override
    public CustomerDTO deleteCustomerDTOByID(Integer customerID) throws CustomerException {
        CustomerDTO customerDTO =getCustomerDTOByID(customerID);
        if (customerDTO==null) {
            throw new CustomerException("Customer not found with ID: " + customerID);
        }
        customerRepository.deleteById(customerID);
        return customerDTO;
    }



    @Override
    public CustomerDTO updateCustomerName(Integer customerID, String newCustomerName) throws CustomerException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);

        if (optionalCustomer.isEmpty()) {
            throw new CustomerException("Customer not found with ID: " + customerID);
        }

        Customer customer = optionalCustomer.get();
        customer.setCustomerName(newCustomerName);
        customerRepository.save(customer);

        return new  CustomerDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomerPhoneNumber(Integer customerID, String customerPhoneNumber) throws CustomerException {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerException("Customer not found with ID: " + customerID);
        }
        Customer customer = optionalCustomer.get();
        customer.setCustomerPhone(customerPhoneNumber);
    customerRepository.save(customer);
        return new  CustomerDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomerEmail(Integer customerID, String customerEmail) throws CustomerException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerException("Customer not found with ID: " + customerID);
        }
        Customer customer = optionalCustomer.get();
        customer.setCustomerEmail(customerEmail);
        customerRepository.save(customer);
        return new  CustomerDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomerAddress(Integer customerID, String customerAddress) throws CustomerException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerException("Customer not found with ID: " + customerID);
        }
        Customer customer = optionalCustomer.get();
        customer.setCustomerAddress(customerAddress);
        customerRepository.save(customer);
        return new  CustomerDTO(customer);
    }
    @Override
    public List<History> getLast10TransactionsByCustomerId(Integer customerId) {
        return historyRepository.findTop10ByCustomerIdOrderByTransactionDateDesc(customerId);
    }

}