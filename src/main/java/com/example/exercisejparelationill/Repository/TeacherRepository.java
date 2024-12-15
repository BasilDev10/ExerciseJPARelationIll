package com.example.exercisejparelationill.Repository;

import com.example.exercisejparelationill.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    Teacher findTeacherById(Integer id);
}
