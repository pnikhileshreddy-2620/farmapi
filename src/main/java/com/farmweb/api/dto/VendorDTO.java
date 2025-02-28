package com.farmweb.api.dto;

import com.farmweb.api.model.Vendor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class VendorDTO {
    private Integer vendorId;
    @NotBlank(message = "Vendor name is required")
    @Size(min = 3, max = 20, message = "admin name must be between 3 and 50 characters")
    private String vendorName;

    @NotBlank(message = "Vendor address is required")
    @Size(min = 5, max = 50, message = "Address must be between 5 and 100 characters")
    private String vendorAddress;

    @NotBlank(message = "Vendor email is required")
    @Email(message = "Invalid email format")
    private String vendorEmail;

    @NotBlank(message = "Vendor phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String vendorPhone;

    @NotBlank(message = "Vendor password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String vendorPassword;

    private String errorMessage;

    public VendorDTO(String vendorName, String vendorAddress, String vendorEmail, String vendorPhone, String vendorPassword) {
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorEmail = vendorEmail;
        this.vendorPhone = vendorPhone;
        this.vendorPassword = vendorPassword;

    }

    public VendorDTO(Vendor vendor) {
        this.vendorId=vendor.getVendorId();
        this.vendorName = vendor.getVendorName();
        this.vendorAddress = vendor.getVendorAddress();
        this.vendorEmail = vendor.getVendorEmail();
        this.vendorPhone = vendor.getVendorPhone();
    }

    public VendorDTO(String message) {
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public VendorDTO() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
