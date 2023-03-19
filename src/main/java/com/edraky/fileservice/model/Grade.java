package com.edraky.fileservice.model;

import com.edraky.fileservice.dto.GradeDto;
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
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)

    private School school;
    private Date createdAt = new Date();
    private Boolean deleted = false;


    public Grade(GradeDto gradeDto) {
        this.name= gradeDto.getName();



    }
}
