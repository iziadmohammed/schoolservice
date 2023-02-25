package com.stc.fileservice.requestmodel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateFileRequest {

    private String spaceName ;


}
