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


    @Column(nullable = false, length = 100)
    private String name;


    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 50)
    private String major;

    @ManyToMany
    private List<Courses> courses;
}
