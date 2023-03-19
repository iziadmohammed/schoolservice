package com.edraky.fileservice.service;

import com.edraky.fileservice.model.School;
import com.edraky.fileservice.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SchoolService implements Crud<School> {
    @Autowired
    private SchoolRepository schoolRepository;
    @Override
    public School getById(Long Id) {
        return schoolRepository.getById(Id);
    }

    @Override
    public School save(School object) {
        return schoolRepository.save(object);
    }

    public boolean existsByName(String name) {
        return schoolRepository.existsByName(name);
    }
}
