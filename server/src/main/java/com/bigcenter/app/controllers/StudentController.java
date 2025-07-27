package com.bigcenter.app.controllers;

import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.services.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody CreateStudentDTO dto) {
        String result = studentService.createStudent(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Set<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody UpdateStudentDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Deleted student successfully!");
    }
}
