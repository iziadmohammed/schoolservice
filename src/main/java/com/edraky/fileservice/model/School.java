package com.edraky.fileservice.model;

import com.edraky.fileservice.dto.SchoolDto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize
@ToString
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public School(SchoolDto schoolDto) {
        this.name = schoolDto.getName();
    }

    private String name;
    private Date createdAt = new Date();
    private Boolean deleted = false;

}
