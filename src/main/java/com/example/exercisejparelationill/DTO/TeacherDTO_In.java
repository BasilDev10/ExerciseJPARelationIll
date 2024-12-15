package com.example.exercisejparelationill.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TeacherDTO_In {

    private Integer id;

    @NotEmpty(message = "Name must not be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Minimum age must be 18")
    private Integer age;

    @NotEmpty(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive number")
    private Double salary;


    @NotEmpty(message = "Area must not be blank")
    @Size(min = 2, max = 100, message = "Area must be between 2 and 100 characters")
    private String area;

    @NotEmpty(message = "Street must not be blank")
    @Size(min = 2, max = 100, message = "Street must be between 2 and 100 characters")
    private String street;

    @NotNull(message = "Building number is required")
    @Min(value = 1, message = "Building number must be at least 1")
    private Integer buildingNumber;
}
