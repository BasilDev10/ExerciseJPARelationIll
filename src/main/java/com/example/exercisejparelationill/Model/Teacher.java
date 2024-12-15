package com.example.exercisejparelationill.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 100)
    private String name;


    @Column(nullable = false)
    private Integer age;


    @Column(nullable = false, unique = true, length = 150)
    private String email;


    @Column(nullable = false)
    private Double salary;


    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "teacher")
    private Set<Courses> courses;
}
