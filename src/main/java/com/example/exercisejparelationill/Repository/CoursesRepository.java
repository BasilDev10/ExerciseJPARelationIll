package com.example.exercisejparelationill.Repository;

import com.example.exercisejparelationill.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Integer> {
    Courses findCoursesById(Integer id);
}
