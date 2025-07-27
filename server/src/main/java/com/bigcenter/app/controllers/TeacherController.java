package com.bigcenter.app.controllers;

import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.services.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<String> createTeacher(@RequestBody CreateTeacherDTO dto) {
        return ResponseEntity.ok(teacherService.createTeacher(dto));
    }

    @GetMapping
    public ResponseEntity<Set<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(teacherService.getTeacher(id));
    }

    @PutMapping
    public ResponseEntity<Teacher> updateTeacher(@RequestBody UpdateTeacherDTO dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
