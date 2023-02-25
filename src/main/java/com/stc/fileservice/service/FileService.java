package com.stc.fileservice.service;

import com.stc.fileservice.model.File;
import com.stc.fileservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public File saveFile(File file) {
        return fileRepository.save(file);
    }
    public File getFile(Long id) {
        return fileRepository.findFileById(id);
    }


}