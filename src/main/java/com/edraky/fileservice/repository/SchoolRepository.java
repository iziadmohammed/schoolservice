package com.edraky.fileservice.repository;

import com.edraky.fileservice.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {

    School getById(Long Id);
    Boolean existsByName(String name);

}
