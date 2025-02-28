package com.farmweb.api.controller;


import com.farmweb.api.dto.AdminDTO;
import com.farmweb.api.dto.VendorDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.exception.VendorException;
import com.farmweb.api.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    // vendor save endpoint is working
    @PostMapping("/save")
    public ResponseEntity<String> createVendor(@Valid @RequestBody VendorDTO vendorDTO) {
        vendorService.createVendor(vendorDTO);
        return new ResponseEntity<>("Vendor Created", HttpStatus.OK);
    }
    // vendor login endpoint is working
    @PostMapping("/login")
    public String login(@RequestBody VendorDTO vendorDTO) {
        return vendorService.vendorLogin(vendorDTO);
    }

    // vendor login endpoint is working
    @GetMapping("/{vendorId}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable Integer vendorId) {
        VendorDTO vendorDTO = vendorService.getVendorDTOById(vendorId);
        return new ResponseEntity<>(vendorDTO, HttpStatus.OK);

    }
    // This endpoint is working
@DeleteMapping("/delete/{vendorId}")
    public ResponseEntity<?> deleteVendor(@PathVariable Integer vendorId) {
        try {
           VendorDTO  vendorDTO = vendorService.deleteVendor(vendorId);
            System.out.println(1);
            return ResponseEntity.ok(vendorDTO);
        }catch (VendorException vendorException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(vendorException.getMessage());
        }
    }

    @PutMapping("/{vendorId}/update-name")
    public ResponseEntity<VendorDTO> updateVendorName(
            @PathVariable Integer vendorId,
            @RequestBody Map<String, String> requestBody) {
        String vendor = requestBody.get("vendorName");
        if (vendor.isEmpty()) {
            return ResponseEntity.badRequest().body(new VendorDTO("Error: Name cannot be empty"));
        }
        try {
            VendorDTO vendorDTO = vendorService.updateVendorName(vendorId, vendor);
            return ResponseEntity.ok(vendorDTO);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VendorDTO(e.getMessage()));
        }
    }
//    @PutMapping("/{vendorId}/update-Email")
//    public ResponseEntity<VendorDTO> updateVendorEmail(
//            @PathVariable Integer vendorId,
//            @RequestBody Map<String, String> requestBody) {
//        String vendor = requestBody.get("vendorEmail");
//        if (vendor.isEmpty()) {
//            return ResponseEntity.badRequest().body(new VendorDTO("Error: Email cannot be empty"));
//        }
//        try {
//            VendorDTO vendorDTO = vendorService.updateVendorEmail(vendorId, vendor);
//            return ResponseEntity.ok(vendorDTO);
//        } catch (CustomerException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VendorDTO(e.getMessage()));
//        }
//    }
//    @PutMapping("/{vendorId}/update-Phone")
//    public ResponseEntity<VendorDTO> updateVendor(
//            @PathVariable Integer vendorId,
//            @RequestBody Map<String, String> requestBody) {
//        String vendor = requestBody.get("vendorPhone");
//        if (vendor.isEmpty()) {
//            return ResponseEntity.badRequest().body(new VendorDTO("Error: Phone Number cannot be empty"));
//        }
//        try {
//            VendorDTO vendorDTO = vendorService.updateVendorPhone(vendorId, vendor);
//            return ResponseEntity.ok(vendorDTO);
//        } catch (CustomerException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VendorDTO(e.getMessage()));
//        }
//    }
//    @PutMapping("/{vendorId}/update-name")
//    public ResponseEntity<VendorDTO> updateVendorAddress(
//            @PathVariable Integer vendorId,
//            @RequestBody Map<String, String> requestBody) {
//        String vendor = requestBody.get("vendorAddress");
//        if (vendor.isEmpty()) {
//            return ResponseEntity.badRequest().body(new VendorDTO("Error: Address cannot be empty"));
//        }
//        try {
//            VendorDTO vendorDTO = vendorService.updateVendorAddress(vendorId, vendor);
//            return ResponseEntity.ok(vendorDTO);
//        } catch (CustomerException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VendorDTO(e.getMessage()));
//        }
//    }
}
