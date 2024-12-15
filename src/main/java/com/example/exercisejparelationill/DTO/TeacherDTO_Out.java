package com.example.exercisejparelationill.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class TeacherDTO_Out {

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

}
