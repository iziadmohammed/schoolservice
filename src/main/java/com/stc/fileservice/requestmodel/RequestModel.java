package com.stc.fileservice.requestmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModel {
    private String spaceId;
    private String spaceName;
    private String parentFolderId;
    private Long parentFolder;
    private Long permissionGroupId ;


}