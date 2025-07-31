package com.bigcenter.app.controllers;

import com.bigcenter.app.services.class_student.ClassesStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/classes-students")
public class ClassesStudentController {

    private final ClassesStudentService classesStudentService;

    public ClassesStudentController(ClassesStudentService classesStudentService) {
        this.classesStudentService = classesStudentService;
    }

    // Enroll student to a class
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(
            @RequestParam UUID studentId,
            @RequestParam UUID classId
    ) {
        classesStudentService.enrollStudent(studentId, classId);
        return ResponseEntity.ok("Student enrolled successfully");
    }

    // Remove student from a class
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeStudentFromClass(
            @RequestParam UUID studentId,
            @RequestParam UUID classId
    ) {
        classesStudentService.removeStudentFromClass(studentId, classId);
        return ResponseEntity.ok("Student removed from class");
    }

    // Get students in a class
    @GetMapping("/class/{classId}/students")
    public ResponseEntity<?> getStudentsInClass(@PathVariable UUID classId) {
        return ResponseEntity.ok(classesStudentService.getStudentsInClass(classId));
    }

    // Get classes of a student
    @GetMapping("/student/{studentId}/classes")
    public ResponseEntity<?> getClassesOfStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(classesStudentService.getClassesOfStudent(studentId));
    }
}
