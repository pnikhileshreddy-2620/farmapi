package com.farmweb.api.serviceImpl;

import com.farmweb.api.dto.AdminDTO;
import com.farmweb.api.model.Admin;
import com.farmweb.api.repository.AdminRepository;
import com.farmweb.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void createAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setAdminEmail(adminDTO.getAdminEmail());
        admin.setAdminPassword(passwordEncoder.encode(adminDTO.getAdminPassword()));
        admin.setAdminName(adminDTO.getAdminName());
        admin.setAdminPhone(adminDTO.getAdminPhone());
        admin.setAdminAddress(adminDTO.getAdminAddress());
        adminRepository.save(admin);
    }

    @Override
    public String loginAdmin(AdminDTO adminDTO) {
        Admin admin = adminRepository.findByAdminName(adminDTO.getAdminName());
         if (admin!=null &&passwordEncoder.matches(adminDTO.getAdminPassword(), admin.getAdminPassword())) {
            return "Admin Login Success";
         }

       return "Admin Login Failed";
    }
}
