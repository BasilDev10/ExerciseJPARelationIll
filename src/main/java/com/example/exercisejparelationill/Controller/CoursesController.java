package com.example.exercisejparelationill.Controller;

import com.example.exercisejparelationill.DTO.CoursesDTO_In;
import com.example.exercisejparelationill.DTO.CoursesDTO_Out;
import com.example.exercisejparelationill.Service.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CoursesController {

    private final CoursesService coursesService;


    @GetMapping("/get")
    public ResponseEntity<List<CoursesDTO_Out>> getAllCourses() {
        List<CoursesDTO_Out> courses = coursesService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<CoursesDTO_Out> getCourseById(@PathVariable Integer id) {
        CoursesDTO_Out course = coursesService.getCourseById(id);
        return ResponseEntity.ok(course);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody CoursesDTO_In coursesDTO_In) {
        coursesService.addCourse(coursesDTO_In);
        return ResponseEntity.ok("Course added successfully!");
    }


    @PutMapping("/assign-teacher")
    public ResponseEntity<String> assignTeacherToCourse(@RequestParam Integer course_id, @RequestParam Integer teacher_id) {
        coursesService.assignTeacherToCourses(course_id, teacher_id);
        return ResponseEntity.ok("Teacher assigned to course successfully!");
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateCourse(@RequestBody CoursesDTO_In coursesDTO_In) {
        coursesService.updateCourse(coursesDTO_In);
        return ResponseEntity.ok("Course updated successfully!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        coursesService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully!");
    }
}