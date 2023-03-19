package com.edraky.fileservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter

public class SchoolDto {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message="Invalid Name: Name is null")
    private String name ;


}
