package com.edraky.fileservice.service;

import com.edraky.fileservice.model.SchoolTime;
import com.edraky.fileservice.repository.SchoolTimeRepository;
import com.edraky.fileservice.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SchoolTimeService implements Crud<SchoolTime> {
    @Autowired
    private SchoolTimeRepository schoolTimeRepository;

    @Override
    public SchoolTime getById(Long Id) {
        return schoolTimeRepository.getById(Id);
    }

    @Override
    public SchoolTime save(SchoolTime object) {
        return schoolTimeRepository.save(object);
    }

    public boolean existsByAClass_IdAndClassTime(Long classId, Date classTime) {
        return schoolTimeRepository.existsByClassIdIdAndClassTime(classId, classTime);
    }

    public List<Class> getClassesBySubjectAndTime(String subject, Date classTime) {
        return schoolTimeRepository.getClassesBySubjectAndTime(subject, classTime);
    }
    public List<Class> getSchoolClassesBySubjectAndTime(String subject,Long schoolId, Date classTime) {
        return schoolTimeRepository.getSchoolClassesBySubjectAndTime(subject,schoolId, classTime);
    }
    public Long countBySubjectAndGrade(Long gradeId,String subject,Date startDate ,Date endDate){
        return schoolTimeRepository.countBySubjectAndGrade(gradeId,subject,startDate,endDate);
    }
}
