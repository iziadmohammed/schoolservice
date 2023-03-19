package com.edraky.fileservice.model;

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
public class SchoolTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date classTime ;
    private String subject ; // I use subject as string for simplicity
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id" ,nullable = false)
    private Class classId ;
    private Date createdAt = new Date();
    private Boolean deleted = false;

}
