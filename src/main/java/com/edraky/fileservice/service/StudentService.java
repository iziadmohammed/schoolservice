package com.edraky.fileservice.service;

import com.edraky.fileservice.model.Student;
import com.edraky.fileservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements Crud<Student> {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public Student save(Student object) {
        return studentRepository.save(object);
    }
}
