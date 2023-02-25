package com.stc.fileservice.service;

import com.stc.fileservice.model.Item;
import com.stc.fileservice.model.PermissionGroup;
import com.stc.fileservice.repository.ItemRepository;
import com.stc.fileservice.repository.PermissionGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionGService {
    @Autowired
    private PermissionGRepository permissionGRepository;

    public PermissionGroup getPermissionGByName(String permission){
        return  permissionGRepository.findPermissionGroupByGroupName(permission);
    }
}
