package com.edraky.fileservice.service;

import com.edraky.fileservice.model.Grade;
import com.edraky.fileservice.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GradeService implements Crud<Grade> {
    @Autowired
    private GradeRepository gradeRepositrory;
    @Override
    public Grade getById(Long id) {
        return gradeRepositrory.getById(id);
    }

    @Override
    public Grade save(Grade object) {
        return gradeRepositrory.save(object);
    }
    
    public Set<Grade> getALl() {
        return null
                ;
    }

    public boolean existsByNameAndSchoolId(String name, Long id) {
        return gradeRepositrory.existsByNameAndSchoolId(name,id);
    }
}
