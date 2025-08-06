package com.bigcenter.app.controllers.api;

import com.bigcenter.app.dtos.requests.subject.SubjectDto;
import com.bigcenter.app.dtos.responses.SubjectResponseDTO;
import com.bigcenter.app.services.subject.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // Thêm môn học
    @PostMapping
    public ResponseEntity<String> addSubject(@RequestBody SubjectDto subjectDto) {
        String result = subjectService.addSubject(subjectDto);
        return ResponseEntity.ok(result);
    }

    // Lấy danh sách môn học
    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    // Xóa môn học theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable UUID id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.ok("Subject deleted successfully");
    }
}
