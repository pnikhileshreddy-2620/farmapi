package com.farmweb.api.service;

import com.farmweb.api.dto.AdminDTO;
import com.farmweb.api.exception.AdminException;
import com.farmweb.api.model.Admin;

public interface AdminService {

    void createAdmin(AdminDTO adminDTO);
    String loginAdmin(AdminDTO adminDTO);
    AdminDTO adminDTOgetById(Integer adminId) throws AdminException;
    AdminDTO deleteAdmin(Integer adminId) throws AdminException;
    AdminDTO updateAdminName(Integer adminId,String adminName) throws AdminException;
    AdminDTO updateAdminEmail(Integer adminId,String  adminEmail) throws AdminException;
    AdminDTO updateAdminPhone(Integer adminId,String adminPhone) throws AdminException;
    AdminDTO updateAdminAddress(Integer adminId,String adminAddress) throws AdminException;

}
