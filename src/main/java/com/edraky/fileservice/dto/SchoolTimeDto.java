package com.edraky.fileservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter

public class SchoolTimeDto {
    @NotBlank(message = "Invalid subject: Empty name")
    @NotNull(message = "Invalid subject: Subject is null")
    private String subject;
    @Min(value = 1)
    private int classId;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date classTime;


}
