package com.example.exercisejparelationill.Service;

import com.example.exercisejparelationill.ApiResponse.ApiException;
import com.example.exercisejparelationill.DTO.StudentDTO_In;
import com.example.exercisejparelationill.DTO.StudentDTO_Out;
import com.example.exercisejparelationill.Model.Courses;
import com.example.exercisejparelationill.Model.Student;
import com.example.exercisejparelationill.Repository.CoursesRepository;
import com.example.exercisejparelationill.Repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;

    public List<StudentDTO_Out> getAllStudents() {
        return studentRepository.findAll().stream().map(this::convertToDTOOut).collect(Collectors.toList());
    }

    public StudentDTO_Out getStudentById(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) throw new ApiException("Error : Student not found");
        return convertToDTOOut(student);
    }

    public void createStudent(StudentDTO_In StudentDTO_In) {
        Student student = new Student();
        student.setName(StudentDTO_In.getName());
        student.setAge(StudentDTO_In.getAge());
        student.setMajor(StudentDTO_In.getMajor());

        if (StudentDTO_In.getCourseIds() != null) {
            List<Courses> courses = coursesRepository.findAllById(StudentDTO_In.getCourseIds());
            student.setCourses(courses);
        }

       studentRepository.save(student);
    }

    public void updateStudent(Integer id, StudentDTO_In StudentDTO_In) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) throw new ApiException("Error : Student not found");
        if (!student.getMajor().equals(StudentDTO_In.getMajor())) {
            student.getCourses().clear();
        }

        student.setName(StudentDTO_In.getName());
        student.setAge(StudentDTO_In.getAge());
        student.setMajor(StudentDTO_In.getMajor());

        if (StudentDTO_In.getCourseIds() != null) {
            List<Courses> courses = coursesRepository.findAllById(StudentDTO_In.getCourseIds());
            student.setCourses(courses);
        }

        studentRepository.save(student);

    }
    public void changeStudentMajor(Integer id, String newMajor) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) throw new ApiException("Error: Student not found");
        student.setMajor(newMajor);
        student.getCourses().clear();
        studentRepository.save(student);
    }
    public void deleteStudent(Integer id) {
        if (studentRepository.findStudentById(id) == null) throw new ApiException("Error : Student not found");
        studentRepository.deleteById(id);
    }


    public void assignStudentToCourses(Integer student_id, Integer course_id){
        Student student = studentRepository.findStudentById(student_id);
        Courses courses = coursesRepository.findCoursesById(course_id);
        if (student == null) throw new ApiException("Error: Student not found");
        if (courses == null) throw new ApiException("Error: Course not found");
        student.getCourses().add(courses);
        courses.getStudents().add(student);
        studentRepository.save(student);
        coursesRepository.save(courses);
    }

    private StudentDTO_Out convertToDTOOut(Student student) {
        List<String> courseNames = student.getCourses().stream().map(Courses::getName).collect(Collectors.toList());
        return new StudentDTO_Out(student.getId(), student.getName(), student.getAge(), student.getMajor(), courseNames);
    }
}