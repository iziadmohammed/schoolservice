package com.stc.fileservice.controller;

import com.stc.fileservice.model.File;
import com.stc.fileservice.model.Item;
import com.stc.fileservice.model.Permission;
import com.stc.fileservice.model.PermissionGroup;
import com.stc.fileservice.model.enums.ItemType;
import com.stc.fileservice.model.enums.PermissionLevel;
import com.stc.fileservice.requestmodel.CreateFileRequest;
import com.stc.fileservice.requestmodel.CreateFolderRequest;
import com.stc.fileservice.requestmodel.RequestModel;
import com.stc.fileservice.service.FileService;
import com.stc.fileservice.service.ItemService;
import com.stc.fileservice.service.PermissionGService;
import com.stc.fileservice.service.PermissionService;
import org.apache.coyote.http11.filters.IdentityOutputFilter;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.stc.fileservice.requestmodel.Constant.*;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private FileService fileService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionGService permissionGService;

    @QueryMapping
    public Item getItemGraph(@Argument Long id) {
      return itemService.getItemById(id);

    }
    @PostMapping(value = "/createSpace")
    public ResponseEntity<Object> createSpace(@RequestBody RequestModel requestModel) {
        String spaceName = requestModel.getSpaceName() != null ? requestModel.getSpaceName() : DEFAULT_SPACE_NAME;
        if (itemService.checkIfItemExist(spaceName, ItemType.Space, null))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Item Already Created");
        Item item = itemService.createItemFromName(spaceName, ItemType.Space);
        PermissionGroup adminGroup = permissionGService.getPermissionGByName("admin"); // getting admin to complete the task
        item.setPermissionGroup(adminGroup);
        return ResponseEntity.status(HttpStatus.OK).body(itemService.saveItem(item));

    }


    @PostMapping(value = "/createFolder")
    public ResponseEntity<Object> createFolder(@RequestHeader("Authorization") String token, @RequestBody CreateFolderRequest requestModel) {
        if (token.isBlank() || token.isEmpty()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(TOKEN_NOTFOUND);
        String folderName = requestModel.getFolderName() != null ? requestModel.getFolderName() : DEFAULT_FOLDER_NAME;
        Long parentId = requestModel.getSpaceId();
        Item parent = null;
        // checking for parent if provided or getting the default space stc-assessments
        if (parentId == null) {
            parent = itemService.getItem(DEFAULT_SPACE_NAME, ItemType.Space, null);
            if (parent == null)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Folder Default Space Not Created yet,please Provide space id or create default space");
        } else {
            parent = itemService.getItemById(parentId);
            if (parent == null || parent.getId() == null)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SPACE_NOTFOUND);
        }

        Permission userPermission = permissionService.getPermissionByUserEmail(token);
        if (userPermission == null || userPermission.getId() == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(USER_NOTFOUND);

        if (userPermission.getPermissionLevel() != PermissionLevel.EDIT || !userPermission.getPermissionGroup().equals(parent.getPermissionGroup()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(INSUFFICIENT_PERMISSION);

        if (itemService.checkIfItemExist(folderName, ItemType.Folder, parent))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(FOLDER_ALREADY_EXIST);


        Item item = itemService.createItemFromName(folderName, ItemType.Folder);
        item.setParentItem(parent);
        PermissionGroup adminGroup = permissionGService.getPermissionGByName("admin"); // getting admin to complete the task
        item.setPermissionGroup(adminGroup);
        return ResponseEntity.status(HttpStatus.OK).body(itemService.saveItem(item));

    }


    @PostMapping(value = "/createFile")
    public ResponseEntity<Object> createFile(@RequestHeader("Authorization") String token,
                                               @RequestParam(value = "file", required = true) MultipartFile file,
                                               @RequestParam(value = "folderId", required = false) Long folderId) throws IOException {
        if (token.isBlank() || token.isEmpty()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(TOKEN_NOTFOUND);
        String fileName = file.getOriginalFilename() != null ? file.getOriginalFilename() : DEFAULT_FILE_NAME;
        Long parentId = folderId;
        Item parent = null;
        // checking for parent if provided or getting the default space stc-assessments

        parent = itemService.getItemById(parentId);
        if (parent == null || parent.getId() == null || parent.getType() != ItemType.Folder)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(FOLDER_NOT_FOUND);


        Permission userPermission = permissionService.getPermissionByUserEmail(token);
        if (userPermission == null || userPermission.getId() == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(USER_NOTFOUND);

        if (userPermission.getPermissionLevel() != PermissionLevel.EDIT || !userPermission.getPermissionGroup().equals(parent.getPermissionGroup()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(INSUFFICIENT_PERMISSION);

        if (itemService.checkIfItemExist(fileName, ItemType.File, parent))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(FILE_ALREADY_EXIST);


        byte[] byteArr = file.getBytes();

        Item item = itemService.createItemFromName(fileName, ItemType.File);
        item.setParentItem(parent);
        PermissionGroup adminGroup = permissionGService.getPermissionGByName("admin"); // getting admin to complete the task
        item.setPermissionGroup(adminGroup);
        File dbFile = new File();
        dbFile.setItem(item);
        dbFile.setData(byteArr);
        Item dbItem = itemService.saveItem(item);

        return ResponseEntity.status(HttpStatus.OK).body(fileService.saveFile(dbFile));

    }
}
