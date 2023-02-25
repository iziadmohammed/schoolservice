package com.stc.fileservice.requestmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFolderRequest {

    private String folderName ;
    private Long spaceId ;

}
