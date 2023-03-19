package com.edraky.fileservice.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter

public class GradeDto {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message="Invalid Name: Name is null")
    private String name ;
    @NotNull(message="Invalid School id : Grade School id is Null")
    @Min(value = 1,message="Invalid School id : Grade School id can't be less than 1")
    private Long schoolId ;


}
