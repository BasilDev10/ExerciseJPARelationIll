package com.example.exercisejparelationill.Controller;

import com.example.exercisejparelationill.ApiResponse.ApiResponse;
import com.example.exercisejparelationill.DTO.StudentDTO_In;
import com.example.exercisejparelationill.DTO.StudentDTO_Out;
import com.example.exercisejparelationill.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<List<StudentDTO_Out>> getAllStudents() {
        List<StudentDTO_Out> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentDTO_Out> getStudentById(@PathVariable Integer id) {
        StudentDTO_Out student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createStudent(@RequestBody StudentDTO_In studentDTO_In) {
        studentService.createStudent(studentDTO_In);
        return ResponseEntity.ok(new ApiResponse("Student created successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO_In studentDTO_In) {
        studentService.updateStudent(id, studentDTO_In);
        return ResponseEntity.ok(new ApiResponse("Student updated successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ApiResponse("Student deleted successfully!"));
    }

    @PostMapping("/assign-student-to-course/{student_id}/{course_id}")
    public ResponseEntity<ApiResponse> assignStudentToCourses(
            @PathVariable Integer student_id, @PathVariable Integer course_id) {
        studentService.assignStudentToCourses(student_id, course_id);
        return ResponseEntity.ok(new ApiResponse("Student assigned to course successfully!"));
    }

    @PutMapping("/change-major/{id}")
    public ResponseEntity<ApiResponse> changeStudentMajor(@PathVariable Integer id, @RequestParam String newMajor) {
        studentService.changeStudentMajor(id, newMajor);
        return ResponseEntity.ok(new ApiResponse("Student's major updated and courses cleared successfully!"));
    }
}