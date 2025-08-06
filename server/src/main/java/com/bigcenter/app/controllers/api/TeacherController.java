package com.bigcenter.app.controllers.api;

import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.dtos.responses.TeacherResponseDTO;
import com.bigcenter.app.services.teacher.TeacherService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // ✅ Hỗ trợ phân trang + Content-Range cho React Admin
    @GetMapping
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers(
            @RequestParam(name = "_start", defaultValue = "0") int start,
            @RequestParam(name = "_end", defaultValue = "10") int end
    ) {
        List<TeacherResponseDTO> allTeachers = teacherService.getAllTeachers();
        int total = allTeachers.size();

        int toIndex = Math.min(end, total);
        List<TeacherResponseDTO> page = allTeachers.subList(start, toIndex);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "teachers " + start + "-" + (toIndex - 1) + "/" + total);
        headers.add("Access-Control-Expose-Headers", "Content-Range");

        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacher(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(teacherService.getTeacher(id));
    }

    @PutMapping
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@RequestBody UpdateTeacherDTO dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
