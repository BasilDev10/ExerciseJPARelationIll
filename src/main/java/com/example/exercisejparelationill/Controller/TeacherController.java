package com.example.exercisejparelationill.Controller;

import com.example.exercisejparelationill.ApiResponse.ApiResponse;
import com.example.exercisejparelationill.DTO.TeacherDTO_In;
import com.example.exercisejparelationill.DTO.TeacherDTO_Out;
import com.example.exercisejparelationill.Service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping("/get")
    public ResponseEntity<List<TeacherDTO_Out>> getAllTeachers() {
        List<TeacherDTO_Out> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TeacherDTO_Out> getTeacherById(@PathVariable Integer id) {
        TeacherDTO_Out teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTeacher(@RequestBody TeacherDTO_In teacherDTO_In) {
        teacherService.addTeacher(teacherDTO_In);
        return ResponseEntity.ok(new ApiResponse("Teacher added successfully!"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateTeacher(@RequestBody TeacherDTO_In teacherDTO_In) {
        teacherService.updateAddress(teacherDTO_In);
        return ResponseEntity.ok(new ApiResponse("Teacher and address updated successfully!"));
    }
    @PostMapping("/assign-teacher-to-course/{teacher_id}/{course_id}")
    public ResponseEntity<ApiResponse> assignTeacherToCourses(
            @PathVariable Integer teacher_id, @PathVariable Integer course_id) {
        teacherService.assignTeacherToCourses(teacher_id, course_id);
        return ResponseEntity.ok(new ApiResponse("Teacher assigned to course successfully!"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok(new ApiResponse("Teacher deleted successfully!"));
    }
}
