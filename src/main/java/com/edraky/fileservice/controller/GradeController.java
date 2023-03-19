package com.edraky.fileservice.controller;

import com.edraky.fileservice.dto.Constant;
import com.edraky.fileservice.dto.GradeDto;
import com.edraky.fileservice.model.Grade;
import com.edraky.fileservice.model.School;
import com.edraky.fileservice.service.GradeService;
import com.edraky.fileservice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public ResponseEntity<?> createGrade(@Valid @RequestBody GradeDto gradeDto) {
        boolean isExist = gradeService.existsByNameAndSchoolId(gradeDto.getName(), gradeDto.getSchoolId());
        if (isExist)
            return new ResponseEntity<>(Constant.GRADE_ALREADY_EXIST, HttpStatus.CONFLICT);

        School school = schoolService.getById(gradeDto.getSchoolId());
        if (school == null || school.getId() <= 0)
            return new ResponseEntity<>(Constant.SCHOOL_NOTFOUND, HttpStatus.NOT_FOUND);

        Grade grade = new Grade();
        grade.setName(gradeDto.getName());
        grade.setSchool(school);
        gradeService.save(grade);
        return new ResponseEntity<>(grade, HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "id") Long id) {

        Grade grade = gradeService.getById(id);
        if (grade == null || grade.getName().isEmpty())
            return new ResponseEntity<>(Constant.GRADE_NOTFOUND, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") Long id) {

        Grade grade = gradeService.getById(id);
        if (grade == null || grade.getName().isEmpty())
            return new ResponseEntity<>(Constant.SCHOOL_NOTFOUND, HttpStatus.NOT_FOUND);
        grade.setDeleted(true);
        gradeService.save(grade);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }
}
