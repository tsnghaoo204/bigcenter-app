package com.bigcenter.app.controllers.api;

import com.bigcenter.app.dtos.requests.grade.GradeDto;
import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.services.grade.GradeService;
import com.bigcenter.app.services.student.StudentService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
public class StudentController {

    private final StudentService studentService;
    private final GradeService gradeService;


    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody CreateStudentDTO dto) {
        String result = studentService.createStudent(dto);
        return ResponseEntity.ok(result);
    }

    // ✅ Hỗ trợ phân trang + header Content-Range cho React Admin
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(
            @RequestParam(name = "_start", defaultValue = "0") int start,
            @RequestParam(name = "_end", defaultValue = "10") int end
    ) {
        List<StudentResponseDTO> allStudents = studentService.getAllStudents();
        int total = allStudents.size();

        int toIndex = Math.min(end, total);
        List<StudentResponseDTO> page = allStudents.subList(start, toIndex);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "students " + start + "-" + (toIndex - 1) + "/" + total);
        headers.add("Access-Control-Expose-Headers", "Content-Range");

        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PutMapping
    @PermitAll
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestBody UpdateStudentDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Deleted student successfully!");
    }

    @PostMapping("/grade")
    public ResponseEntity<String> addGrade(@RequestParam(name = "studentId") UUID studentId,
                                           @RequestParam(name = "classId") UUID classId,
                                           @RequestParam(name = "subjectId") UUID subjectId,
                                           GradeDto dto){
        return ResponseEntity.ok(gradeService.saveGrade(studentId, classId, subjectId,dto));
    }


}
