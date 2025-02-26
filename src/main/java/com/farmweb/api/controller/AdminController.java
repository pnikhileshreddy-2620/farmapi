package com.farmweb.api.controller;

import com.farmweb.api.dto.AdminDTO;
import com.farmweb.api.model.Admin;
import com.farmweb.api.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/save")
    public ResponseEntity<String> saveAdmin(@Valid @RequestBody AdminDTO adminDTO) {
         adminService.createAdmin(adminDTO);
         return ResponseEntity.ok("Admin Saved");
    }
    @PostMapping("/login")
    public String login(@RequestBody AdminDTO adminDTO) {
        return adminService.loginAdmin(adminDTO);
    }

}
