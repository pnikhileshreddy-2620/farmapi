package com.farmweb.api.serviceImpl;

import com.farmweb.api.dto.AdminDTO;
import com.farmweb.api.exception.AdminException;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.model.Admin;
import com.farmweb.api.model.Customer;
import com.farmweb.api.repository.AdminRepository;
import com.farmweb.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Override
    public AdminDTO adminDTOgetById(Integer adminId) throws AdminException {
        AdminDTO adminDTO = new AdminDTO();
        Admin  admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminException("Admin not found with ID: " + adminId));
        adminDTO.setAdminId(admin.getAdminId());
        adminDTO.setAdminName(admin.getAdminName());
        adminDTO.setAdminEmail(admin.getAdminEmail());
        adminDTO.setAdminPhone(admin.getAdminPhone());
        adminDTO.setAdminAddress(admin.getAdminAddress());
        return adminDTO;
    }
    @Override
    public AdminDTO deleteAdmin(Integer adminId) throws AdminException {
        AdminDTO adminDTO = adminDTOgetById(adminId);
        if (adminDTO==null){
            throw new AdminException("Admin not found with ID: " + adminId);
        }
        adminRepository.deleteById(adminId);
        return adminDTO;
    }
    @Override
    public AdminDTO updateAdminName(Integer adminId, String adminName) throws AdminException {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminException("Admin not found with ID: " + adminId));
        admin.setAdminName(adminName);
        adminRepository.save(admin);
        return new AdminDTO(admin);
    }
    @Override
    public AdminDTO updateAdminEmail(Integer adminId, String adminEmail) throws AdminException {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminException("Admin not found with ID: " + adminId));
        admin.setAdminEmail(adminEmail);
        adminRepository.save(admin);
        return new AdminDTO(admin);
    }
    @Override
    public AdminDTO updateAdminPhone(Integer adminId, String adminPhone) throws AdminException {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminException("Admin not found with ID: " + adminId));
        admin.setAdminPhone(adminPhone);
        adminRepository.save(admin);
        return new AdminDTO(admin);
    }
    @Override
    public AdminDTO updateAdminAddress(Integer adminId, String adminAddress) throws AdminException {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminException("Admin not found with ID: " + adminId));
        admin.setAdminAddress(adminAddress);
        adminRepository.save(admin);
        return new AdminDTO(admin);
    }


}
