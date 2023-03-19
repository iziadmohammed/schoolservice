package com.edraky.fileservice.service;

import com.edraky.fileservice.model.Class;
import com.edraky.fileservice.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService implements Crud<Class> {
    @Autowired
    private ClassRepository classRepository;
    @Override
    public Class getById(Long id) {
        return classRepository.getById(id);
    }

    @Override
    public Class save(Class object) {
        return classRepository.save(object);
    }

    public boolean existsByNameAndGradeId (String name , Long gradeId){
        return  classRepository.existsByNameAndGradeId(name,gradeId);
    }


}