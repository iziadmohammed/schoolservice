package com.edraky.fileservice.controller;

import com.edraky.fileservice.dto.Constant;
import com.edraky.fileservice.dto.StudentDto;
import com.edraky.fileservice.model.Student;
import com.edraky.fileservice.model.Class;
import com.edraky.fileservice.service.ClassService;
import com.edraky.fileservice.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.edraky.fileservice.dto.Constant.CLASS_NOTFOUND;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto) {
        Student student = new Student(studentDto);
        Class aClass = classService.getById(studentDto.getClassID());
        if(aClass==null||aClass.getId()==null)
            return new ResponseEntity<>(CLASS_NOTFOUND, HttpStatus.CREATED);
        student.setSClass(aClass);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "id") int id) {

        Student student = studentService.getById(Long.valueOf(id));
        if (student == null || student.getName().isEmpty())
            return new ResponseEntity<>(Constant.STUDENT_NOTFOUND, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") int id) {

        Student student = studentService.getById(Long.valueOf(id));
        if (student == null || student.getName().isEmpty())
            return new ResponseEntity<>(Constant.STUDENT_NOTFOUND, HttpStatus.NOT_FOUND);
        student.setDeleted(true);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
