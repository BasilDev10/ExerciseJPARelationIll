package com.example.exercisejparelationill.Service;

import com.example.exercisejparelationill.ApiResponse.ApiException;
import com.example.exercisejparelationill.DTO.CoursesDTO_In;
import com.example.exercisejparelationill.DTO.CoursesDTO_Out;
import com.example.exercisejparelationill.Model.Courses;
import com.example.exercisejparelationill.Model.Student;
import com.example.exercisejparelationill.Model.Teacher;
import com.example.exercisejparelationill.Repository.CoursesRepository;
import com.example.exercisejparelationill.Repository.StudentRepository;
import com.example.exercisejparelationill.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CoursesRepository coursesRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    public List<CoursesDTO_Out> getAllCourses(){
        List<Courses> courses = coursesRepository.findAll();
        List<CoursesDTO_Out> coursesDTOS = new ArrayList<>();
        for (Courses course : courses) {
            coursesDTOS.add(new CoursesDTO_Out(course.getId(),course.getName()));
        }
        return coursesDTOS;
    }

    public CoursesDTO_Out getCourseById(Integer id){
        Courses courses = coursesRepository.findCoursesById(id);
        return new CoursesDTO_Out(courses.getId(),courses.getName());
    }

    public void addCourse(CoursesDTO_In CoursesDTO_In){
        Courses courses = new Courses();
        Teacher teacher = new Teacher();
        Student student = new Student();
        if (CoursesDTO_In.getTeacher_id() != null){
            teacher = teacherRepository.findTeacherById(CoursesDTO_In.getTeacher_id());
            if (teacher == null) throw new ApiException("Error: Teacher not found");
            courses.setTeacher(teacher);
        }
        courses.setName(CoursesDTO_In.getName());
        courses =coursesRepository.save(courses);

        if (CoursesDTO_In.getStudent_id() != null){
            student = studentRepository.findStudentById(CoursesDTO_In.getStudent_id());
            if (student == null) throw new ApiException("Error: Student not found");
            courses.getStudents().add(student);
            coursesRepository.save(courses);
            student.getCourses().add(courses);
            studentRepository.save(student);
        }

    }


    public void assignTeacherToCourses(Integer course_id, Integer teacher_id){
        Courses courses = coursesRepository.findCoursesById(course_id);
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if (courses == null) throw new ApiException("Error: Course not found");
        if (teacher == null) throw new ApiException("Error: Teacher not found");
        courses.setTeacher(teacher);
        coursesRepository.save(courses);
        teacher.getCourses().add(courses);
        teacherRepository.save(teacher);
    }

    public void updateCourse(CoursesDTO_In CoursesDTO_In){
        Courses courses = coursesRepository.findCoursesById(CoursesDTO_In.getId());
        Teacher teacher = new Teacher();
        Student student = new Student();
        if (CoursesDTO_In.getTeacher_id() != null){
            teacher = teacherRepository.findTeacherById(CoursesDTO_In.getTeacher_id());
            if (teacher == null) throw new ApiException("Error: Teacher not found");
            courses.setTeacher(teacher);
        }
        courses.setName(CoursesDTO_In.getName());
        courses =coursesRepository.save(courses);

        if (CoursesDTO_In.getStudent_id() != null){
            student = studentRepository.findStudentById(CoursesDTO_In.getStudent_id());
            if (student == null) throw new ApiException("Error: Student not found");
            courses.getStudents().add(student);
            coursesRepository.save(courses);
            student.getCourses().add(courses);
            studentRepository.save(student);
        }
    }

    public void deleteCourse(Integer id){
        Courses courses = coursesRepository.findCoursesById(id);
        if (courses == null) throw new ApiException("Error: Course not found");
        coursesRepository.delete(courses);
    }
}
