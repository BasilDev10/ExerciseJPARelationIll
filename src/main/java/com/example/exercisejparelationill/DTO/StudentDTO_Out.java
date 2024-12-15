package com.example.exercisejparelationill.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO_Out {
    private Integer id;
    @NotEmpty(message = "Name must not be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @NotEmpty(message = "Major must not be blank")
    @Size(min = 2, max = 50, message = "Major must be between 2 and 50 characters")
    private String major;

    private List<String> courses;
}
