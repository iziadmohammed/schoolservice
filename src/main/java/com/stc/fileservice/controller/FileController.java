package com.stc.fileservice.controller;

import com.stc.fileservice.model.File;
import com.stc.fileservice.model.Permission;
import com.stc.fileservice.model.enums.PermissionLevel;
import com.stc.fileservice.service.FileService;
import com.stc.fileservice.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;

import static com.stc.fileservice.requestmodel.Constant.*;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping
    public ResponseEntity<?> downloadFile(@RequestHeader("Authorization") String token,@RequestParam(value = "id") Long id ){
        if (token.isBlank() || token.isEmpty()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(TOKEN_NOTFOUND);
        Permission userPermission = permissionService.getPermissionByUserEmail(token);
        if (userPermission == null || userPermission.getId() == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(USER_NOTFOUND);

        File file =fileService.getFile(id);
        if (!userPermission.getPermissionGroup().equals(file.getItem().getPermissionGroup()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(INSUFFICIENT_PERMISSION);

        if(file.getId()==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FILE_NOT_FOUND);


        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getItem().getName());

        ByteArrayResource resource = new ByteArrayResource(file.getData());
        return ResponseEntity.ok().headers(header)
                .contentLength(file.getData().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
