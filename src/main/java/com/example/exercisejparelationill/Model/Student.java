package com.example.exercisejparelationill.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Column(nullable = false)
    private Integer age;

    @NotEmpty(message = "Major must not be blank")
    @Size(min = 2, max = 50, message = "Major must be between 2 and 50 characters")
    @Column(nullable = false, length = 50)
    private String major;

    @ManyToMany
    private List<Courses> courses;
}
