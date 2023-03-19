package com.edraky.fileservice.controller;

import com.edraky.fileservice.dto.Constant;
import com.edraky.fileservice.dto.SchoolDto;
import com.edraky.fileservice.model.School;
import com.edraky.fileservice.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/school")
public class SchoolController {
    private static final Logger log = LoggerFactory.getLogger(SchoolController.class);
    @Autowired
    private SchoolService schoolService ;
    @PostMapping
    public ResponseEntity<?> createSchool(@Valid @RequestBody SchoolDto schoolDto){
        School school = new School(schoolDto);
        boolean isExist = schoolService.existsByName(school.getName());
        if(isExist)
            return new ResponseEntity<>(Constant.SCHOOL_ALREADY_EXIST, HttpStatus.CONFLICT);

        school=schoolService.save(school);

        return new ResponseEntity<>(school, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "id") int id){

        School school = schoolService.getById(Long.valueOf(id));
        if(school==null||school.getName().isEmpty())
            return new ResponseEntity<>(Constant.SCHOOL_NOTFOUND, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(school, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") int id){

        School school = schoolService.getById(Long.valueOf(id));
        if(school==null||school.getName().isEmpty())
            return new ResponseEntity<>(Constant.SCHOOL_NOTFOUND, HttpStatus.NOT_FOUND);
        school.setDeleted(true);
        schoolService.save(school);
        return new ResponseEntity<>(school, HttpStatus.OK);
    }
}
