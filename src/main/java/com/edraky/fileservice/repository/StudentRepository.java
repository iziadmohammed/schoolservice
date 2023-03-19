package com.edraky.fileservice.repository;

import com.edraky.fileservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student getById(Long id );
}
