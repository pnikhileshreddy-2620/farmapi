package com.farmweb.api.dto;

import com.farmweb.api.model.Admin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminDTO {
    private int adminId;
    @NotBlank(message = "Customer name is required")
    @Size(min = 3, max = 20, message = "admin name must be between 3 and 50 characters")
    private String adminName;
    @NotBlank(message = "Admin password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String adminPassword;
    @NotBlank(message = "Admin email is required")
    @Email(message = "Invalid email format")
    private String adminEmail;
    @NotBlank(message = "Admin phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String adminPhone;
    @NotBlank(message = "Admin address is required")
    @Size(min = 5, max = 50, message = "Address must be between 5 and 100 characters")
    private String adminAddress;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AdminDTO(Admin admin) {
        this.adminId =admin.getAdminId();
        this.adminName = admin.getAdminName();
        this.adminEmail = admin.getAdminEmail();
        this.adminPhone = admin.getAdminPhone();
        this.adminAddress = admin.getAdminAddress();

    }

    public AdminDTO(String message) {
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public AdminDTO(String adminName, String adminPassword, String adminEmail, String adminPhone, String adminAddress) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;
        this.adminPhone = adminPhone;
        this.adminAddress = adminAddress;
    }

    public AdminDTO() {
    }
}
