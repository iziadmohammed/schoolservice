package com.stc.fileservice.repository;

import com.stc.fileservice.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<File,String> {

    public File findFileById(Long id);

}
