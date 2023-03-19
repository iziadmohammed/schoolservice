package com.edraky.fileservice.repository;

import com.edraky.fileservice.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, String> {
    Boolean existsByNameAndSchoolId(String name,Long id);
    Grade getById(Long Id);
    Boolean existsByName(String name);
}

