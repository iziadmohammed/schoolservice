package com.edraky.fileservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter

public class StudentDto {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message="Invalid Name: Name is null")
    private String name ;
    @NotNull(message="Invalid Class id : Class id is Null")
    @Min(value = 1,message="Invalid Class id : Class id can't be less than 1")
    private Long classID ;


}
