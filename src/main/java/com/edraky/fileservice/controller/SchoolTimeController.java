package com.edraky.fileservice.controller;

import com.edraky.fileservice.service.ClassService;
import com.edraky.fileservice.dto.SchoolTimeDto;
import com.edraky.fileservice.model.Class;
import com.edraky.fileservice.model.SchoolTime;
import com.edraky.fileservice.service.SchoolTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static com.edraky.fileservice.dto.Constant.*;

@RestController
@RequestMapping(value = "/schoolTime")
public class SchoolTimeController {
    private static final Logger log = LoggerFactory.getLogger(SchoolTimeController.class);
    @Autowired
    private SchoolTimeService schoolTimeService;
    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<?> createSchoolTime(@Valid @RequestBody SchoolTimeDto schoolTimeDto) {
        boolean isExist = schoolTimeService.existsByAClass_IdAndClassTime((long) schoolTimeDto.getClassId(), schoolTimeDto.getClassTime());
        if (isExist)
            return new ResponseEntity<>(CLASS_ALREADY_EXIST, HttpStatus.CONFLICT);
        Class aclass = classService.getById((long) schoolTimeDto.getClassId());
        if (aclass == null || aclass.getId() == null)
            return new ResponseEntity<>(CLASS_NOTFOUND, HttpStatus.NOT_FOUND);

        SchoolTime schoolTime = new SchoolTime();
        schoolTime.setClassTime(schoolTimeDto.getClassTime());
        schoolTime.setClassId(aclass);
        schoolTime.setSubject(schoolTimeDto.getSubject());
        schoolTimeService.save(schoolTime);

        return new ResponseEntity<>(schoolTime, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "id") int id) {
        SchoolTime schoolTime = schoolTimeService.getById(Long.valueOf(id));
        if (schoolTime == null || schoolTime.getSubject().isEmpty())
            return new ResponseEntity<>(SCHOOL_TIME_NOTFOUND, HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(schoolTime, HttpStatus.OK);
    }

    @GetMapping(value = "/getClassesBy")
    public ResponseEntity<?> getClassesBy(@RequestParam(value = "subject") String subject, @RequestParam(value = "classTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date classTime,@RequestParam(value = "schoolId") Long schoolId) {
        List<Class> list = schoolTimeService.getSchoolClassesBySubjectAndTime(subject,schoolId, classTime);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/getClassCount")
    public ResponseEntity<?> getClassesBy(@RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subject") String subject, @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate) {
        Long count = schoolTimeService.countBySubjectAndGrade(gradeId, subject, startDate, endDate);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") int id) {

        SchoolTime schoolTime = schoolTimeService.getById(Long.valueOf(id));
        if (schoolTime == null || schoolTime.getSubject().isEmpty())
            return new ResponseEntity<>(SCHOOL_TIME_NOTFOUND, HttpStatus.NOT_FOUND);
        schoolTime.setDeleted(true);
        schoolTimeService.save(schoolTime);
        return new ResponseEntity<>(schoolTime, HttpStatus.OK);
    }
}
