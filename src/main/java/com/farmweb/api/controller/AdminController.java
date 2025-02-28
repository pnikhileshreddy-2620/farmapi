package com.farmweb.api.controller;

import com.farmweb.api.dto.AdminDTO;
import com.farmweb.api.exception.AdminException;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admins")
public class AdminController {


    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint is working
    @PostMapping("/save")
    public ResponseEntity<String> saveAdmin(@Valid @RequestBody AdminDTO adminDTO) {
         adminService.createAdmin(adminDTO);
         return ResponseEntity.ok("Admin Saved");
    }
    // Endpoint is working
    @PostMapping("/login")
    public String login(@RequestBody AdminDTO adminDTO) {
        return adminService.loginAdmin(adminDTO);
    }
    // Endpoint is working
    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDTO> getCustomer(@PathVariable Integer adminId) {
        AdminDTO adminDTO = adminService.adminDTOgetById(adminId);
        return ResponseEntity.ok(adminDTO);
    }
    //Endpoint is working
    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Integer adminId) {
        try {
            AdminDTO adminDTO = adminService.deleteAdmin(adminId);
            return ResponseEntity.ok(adminDTO);
        }catch (AdminException adminException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(adminException.getMessage());
        }

    }

    @PutMapping("/{adminId}/update-address")
    public ResponseEntity<AdminDTO> updateAdminAddress(
            @PathVariable Integer adminId,
            @RequestBody Map<String, String> requestBody) {
        String adminAddress = requestBody.get("adminAddress");
        if (adminAddress == null || adminAddress.isEmpty()) {
            return ResponseEntity.badRequest().body(new AdminDTO("Error: Address cannot be empty"));
        }
        try {
            AdminDTO adminDTO = adminService.updateAdminAddress(adminId, adminAddress);
            return ResponseEntity.ok(adminDTO);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AdminDTO(e.getMessage()));
        }
    }
    @PutMapping("/{adminId}/update-name")
    public ResponseEntity<AdminDTO> updateAdminName(
            @PathVariable Integer adminId,
            @RequestBody Map<String, String> requestBody) {
        String admin = requestBody.get("adminName");
        if (admin == null || admin.isEmpty()) {
            return ResponseEntity.badRequest().body(new AdminDTO("Error: Name cannot be empty"));
        }
        try {
            AdminDTO adminDTO = adminService.updateAdminName(adminId, admin);
            return ResponseEntity.ok(adminDTO);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AdminDTO(e.getMessage()));
        }
    }
    @PutMapping("/{adminId}/update-email")
    public ResponseEntity<AdminDTO> updateAdminEmail(
            @PathVariable Integer adminId,
            @RequestBody Map<String, String> requestBody) {
        String admin = requestBody.get("adminEmail");
        if (admin.isEmpty()) {
            return ResponseEntity.badRequest().body(new AdminDTO("Error: Email cannot be empty"));
        }
        try {
            AdminDTO adminDTO = adminService.updateAdminEmail(adminId, admin);
            return ResponseEntity.ok(adminDTO);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AdminDTO(e.getMessage()));
        }
    }

    @PutMapping("/{adminId}/update-phone")
    public ResponseEntity<AdminDTO> updateAdminPhone(
            @PathVariable Integer adminId,
            @RequestBody Map<String, String> requestBody) {
        String admin = requestBody.get("adminPhone");
        if (admin.isEmpty()) {
            return ResponseEntity.badRequest().body(new AdminDTO("Error: Phone Number cannot be empty"));
        }
        try {
            AdminDTO adminDTO = adminService.updateAdminPhone(adminId, admin);
            return ResponseEntity.ok(adminDTO);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AdminDTO(e.getMessage()));
        }
    }

}
