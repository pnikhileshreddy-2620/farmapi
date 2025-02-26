package com.farmweb.api.dto;

import com.farmweb.api.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    //Removed the not blank annotation
    private Integer customerId;
    @NotBlank(message = "Customer name is required")
    @Size(min = 3, max = 20, message = "Customer name must be between 3 and 50 characters")
    private String customerName;
    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;
    @NotBlank(message = "Customer phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String customerPhone;
    @NotBlank(message = "Customer address is required")
    @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
    private String customerAddress;
    @NotBlank(message = "Customer password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String customerPassword;

    public CustomerDTO() {}

    public CustomerDTO(String customerName, String customerEmail, String customerPhone, String customerAddress, String customerPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerPassword = customerPassword;
    }

    public CustomerDTO(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerName = customer.getCustomerName();
        this.customerPhone = customer.getCustomerPhone();
        this.customerEmail = customer.getCustomerEmail();
        this.customerAddress = customer.getCustomerAddress();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}