package com.example.employeesmanagemen.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class Employees {


    //ID , name , age , position , onLeave, employmentYear ,annualLeave

    @NotEmpty(message = "Id is Required")
    @Size(min=2 ,message ="The id should be more than 2 numbers")
    private String id;

    @NotEmpty(message = "Name is Required")
    @Size(min=4 ,message ="The name should be more than 4 characters" )
    private String name;

    @NotNull(message = "Age Required")
    @Min(value = 25, message ="The age should be be more than 25 years")
    @Positive(message ="Negative age number can not be accepted")
    private int age;

    @NotEmpty(message = "Position Required")
    @Pattern(regexp ="^(supervisor|coordinator)$", message = "The position should be supervisor or coordinator ")
    private String  position;

    @AssertFalse(message = " the onLeave default value can only false value")
    private  boolean onLeave ;


    //@Pattern(regexp ="^\\d{4}$")
    @NotNull(message = "Employment Year is Required")
    //@Digits(integer = 4, fraction = 0)
    @Min(2000)
    @Max(2050)
    private double employmentYear;


    @NotNull(message = " annualLeave is Required")
   // @Digits(integer = 2, fraction = 0)
    @Max(30)
    @Positive
    private double annualLeave;






}
