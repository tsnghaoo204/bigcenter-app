package com.bigcenter.app.controllers.api;

import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpHeaders;
import com.bigcenter.app.services.classes.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<ClassResponseDTO>> getAllClasses(
        @RequestParam(name = "_start", defaultValue = "0") int start,
        @RequestParam(name = "_end", defaultValue = "10") int end
    ) {
            List<ClassResponseDTO> allClasses = classService.getAllClass();
            int total = allClasses.size();

            int toIndex = Math.min(end, total);
            List<ClassResponseDTO> page = allClasses.subList(start, toIndex);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Range", "classes " + start + "-" + (toIndex - 1) + "/" + total);
            headers.add("Access-Control-Expose-Headers", "Content-Range");

            return ResponseEntity.ok().headers(headers).body(page);
        }
    @GetMapping("/{name}")
    public ResponseEntity<ClassResponseDTO> getClassByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(classService.getClass(name));
    }

    @PutMapping
    public ResponseEntity<ClassResponseDTO> updateClass(@RequestBody UpdateClassDTO dto) {
        return ResponseEntity.ok(classService.updateClass(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClass(@PathVariable("id") UUID id) {
        classService.deleteClass(id);
        return ResponseEntity.ok("Deleted class successfully!");
    }
}
