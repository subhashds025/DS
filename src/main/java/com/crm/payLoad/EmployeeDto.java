package com.crm.payLoad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min = 3,message = "name should have atleast 3 characters")
    private String name;

    @Email(message = "enter proper email with correct syntax")
    private String emailId;

    @Size(min = 10,max = 10,message = "mobile number should have 10 digits")
    private String mobile;

}
