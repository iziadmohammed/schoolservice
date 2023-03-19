package com.edraky.fileservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter

public class ClassDto {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message="Invalid Name: Name is null")
    private String name ;
    @NotNull(message="Invalid Grade id : Class Grade id is Null")
    @Min(value = 1,message="Invalid Grade id : Grade id can't be less than 1")
    private Long gradeId ;


}
