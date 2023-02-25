package com.stc.fileservice.repository;

import com.stc.fileservice.model.Item;
import com.stc.fileservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {

    public Permission findPermissionByUserEmail(String userEmail);
}
