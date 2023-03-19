package com.edraky.fileservice.repository;

import com.edraky.fileservice.model.SchoolTime;
import com.edraky.fileservice.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SchoolTimeRepository extends JpaRepository<SchoolTime, String> {
    List<SchoolTime> getAllBySubjectAndClassTime(String subject, Date classTime);
    SchoolTime getById(Long id);
    boolean existsByClassIdIdAndClassTime(Long classId,Date classTime);
    @Query(value = "SELECT e.classId FROM SchoolTime e where e.subject=:subject and e.classTime=:classTime")
    List<Class> getClassesBySubjectAndTime(String subject , Date classTime);
    @Query("SELECT st.classId from SchoolTime st inner join Class c on st.classId.id = c.id inner join Grade g on c.grade.id = g.id and g.school.id=:schoolId where st.subject=:subject and st.classTime=:classTime")
    List<Class> getSchoolClassesBySubjectAndTime(String subject ,Long schoolId, Date classTime);



    @Query(value = "SELECT count(e) FROM SchoolTime e left join Class c on e.classId.id=c.id and c.grade.id=:gradeId where e.subject=:subject and e.classTime between :startDate and :endDate")
    Long countBySubjectAndGrade(Long gradeId,String subject,Date startDate ,Date endDate);

}
