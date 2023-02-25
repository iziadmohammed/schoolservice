package com.stc.fileservice.repository;

import com.stc.fileservice.model.Item;
import com.stc.fileservice.model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGRepository extends JpaRepository<PermissionGroup,String> {

    PermissionGroup findPermissionGroupByGroupName(String groupName);
}
