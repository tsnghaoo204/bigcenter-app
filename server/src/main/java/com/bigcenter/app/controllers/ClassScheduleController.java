package com.bigcenter.app.controllers;

import com.bigcenter.app.dtos.requests.class_schedule.CreateClassScheduleDTO;
import com.bigcenter.app.dtos.requests.class_schedule.UpdateClassScheduleDTO;
import com.bigcenter.app.dtos.responses.ClassScheduleResponseDTO;
import com.bigcenter.app.entities.ClassSchedule;
import com.bigcenter.app.services.class_schedule.ClassScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ClassScheduleController {

    private final ClassScheduleService classScheduleService;

    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody CreateClassScheduleDTO dto) {
        String message = classScheduleService.createSchedule(dto);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<ClassScheduleResponseDTO>> getAllSchedules() {
        return ResponseEntity.ok(classScheduleService.getAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassScheduleResponseDTO> getSchedule(@PathVariable int id) {
        return ResponseEntity.ok(classScheduleService.getSchedule(id));
    }

    @PutMapping
    public ResponseEntity<ClassScheduleResponseDTO> updateSchedule(@RequestBody UpdateClassScheduleDTO dto) {
        return ResponseEntity.ok(classScheduleService.updateSchedule(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int id) {
        classScheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
