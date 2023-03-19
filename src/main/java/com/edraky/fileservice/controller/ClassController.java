package com.edraky.fileservice.controller;

import com.edraky.fileservice.dto.Constant;
import com.edraky.fileservice.model.Grade;
import com.edraky.fileservice.service.ClassService;
import com.edraky.fileservice.service.GradeService;
import com.edraky.fileservice.service.SchoolService;
import com.edraky.fileservice.dto.ClassDto;
import com.edraky.fileservice.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<?> createClass(@Valid @RequestBody ClassDto classDto) {
        boolean isExist = classService.existsByNameAndGradeId(classDto.getName(), classDto.getGradeId());
        if (isExist) return new ResponseEntity<>(Constant.CLASS_ALREADY_EXIST, HttpStatus.CONFLICT);

        Grade grade = gradeService.getById(classDto.getGradeId());

        if (grade == null || grade.getId() == null) return new ResponseEntity<>(Constant.GRADE_NOTFOUND, HttpStatus.NOT_FOUND);

        Class aclass = new Class();
        aclass.setName(classDto.getName());
        aclass.setGrade(grade);
        classService.save(aclass);

        return new ResponseEntity<>(aclass, HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "id") Long id) {

        Class aClass = classService.getById(id);
        if (aClass == null || aClass.getId() == null) return new ResponseEntity<>(Constant.GRADE_NOTFOUND, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(aClass, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") Long id) {

        Class aClass = classService.getById(id);
        if (aClass == null || aClass.getId() == null)
            return new ResponseEntity<>(Constant.SCHOOL_NOTFOUND, HttpStatus.NOT_FOUND);
        aClass.setDeleted(true);
        classService.save(aClass);
        return new ResponseEntity<>(aClass, HttpStatus.OK);
    }
}
