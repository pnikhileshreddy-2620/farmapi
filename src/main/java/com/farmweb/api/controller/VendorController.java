package com.farmweb.api.controller;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.dto.VendorDTO;
import com.farmweb.api.model.Vendor;
import com.farmweb.api.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private  VendorService vendorService;
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
    public ResponseEntity<VendorDTO>  getVendor(@PathVariable Integer vendorId ) {
    VendorDTO vendorDTO = vendorService.getVendorDTOById(vendorId);
    return new ResponseEntity<>(vendorDTO, HttpStatus.OK);

    }

}
