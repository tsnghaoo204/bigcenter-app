package com.bigcenter.app.controllers;

import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.services.classes.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping
    public ResponseEntity<String> createClass(@RequestBody CreateClassDTO dto) {
        String result = classService.createClass(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Set<Class>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClass());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Class> getClassByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(classService.getClass(name));
    }

    @PutMapping
    public ResponseEntity<Class> updateClass(@RequestBody UpdateClassDTO dto) {
        return ResponseEntity.ok(classService.updateClass(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClass(@PathVariable("id") UUID id) {
        classService.deleteClass(id);
        return ResponseEntity.ok("Deleted class successfully!");
    }
}
