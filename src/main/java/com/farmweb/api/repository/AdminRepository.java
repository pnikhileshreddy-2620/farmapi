package com.farmweb.api.repository;

import com.farmweb.api.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByAdminName(String adminName);
    Admin findByAdminId(Integer adminId);


}
