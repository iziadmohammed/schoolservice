package com.stc.fileservice.service;

import com.stc.fileservice.model.Item;
import com.stc.fileservice.model.Permission;
import com.stc.fileservice.repository.ItemRepository;
import com.stc.fileservice.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    public Permission getPermissionByUserEmail(String userEmail){
        return permissionRepository.findPermissionByUserEmail(userEmail);
    }


}
