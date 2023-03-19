package com.edraky.fileservice.model;


import com.edraky.fileservice.dto.StudentDto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // can be extended to other columns' ex: first and last name etc...
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id" ,nullable = false)
    private Class sClass ;
    private Date createdAt = new Date();
    private Boolean deleted = false;
    public Student(StudentDto studentDto){
        this.name = studentDto.getName();
    }

}
