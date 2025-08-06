package com.bigcenter.app.controllers.api;

import com.bigcenter.app.services.class_student.ClassesStudentService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/class/{classId}/students")
    public ResponseEntity<?> getStudentsInClass(@PathVariable UUID classId) {
        return ResponseEntity.ok(classesStudentService.getStudentsInClass(classId));
    }

    @GetMapping("/student/classes")
    public ResponseEntity<?> getClassesOfStudent(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(classesStudentService.getClassesOfToken(jwt));
    }
}
