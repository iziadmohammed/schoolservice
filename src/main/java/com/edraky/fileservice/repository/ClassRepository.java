package com.edraky.fileservice.repository;

import com.edraky.fileservice.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ClassRepository extends JpaRepository<Class, String> {

    Class getById(Long Id);

    Boolean existsByNameAndGradeId(String name,Long gradeId);
}
