package com.bigcenter.app.services.class_schedule;

import com.bigcenter.app.dtos.mappers.ClassScheduleMapper;
import com.bigcenter.app.dtos.requests.class_schedule.CreateClassScheduleDTO;
import com.bigcenter.app.dtos.requests.class_schedule.UpdateClassScheduleDTO;
import com.bigcenter.app.entities.ClassSchedule;
import com.bigcenter.app.entities.Room;
import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.repositories.ClassScheduleRepository;
import com.bigcenter.app.repositories.RoomRepository;
import com.bigcenter.app.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassScheduleServiceImpl implements ClassScheduleService {

    private final ClassScheduleRepository scheduleRepository;
    private final ClassScheduleMapper classScheduleMapper;
    private final RoomRepository roomRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public String createSchedule(CreateClassScheduleDTO dto) {
        scheduleRepository.save(classScheduleMapper.toEntity(dto));
        return "Schedule created successfully!";
    }

    @Override
    public Set<ClassSchedule> getAllSchedules() {
        return new HashSet<>(scheduleRepository.findAll());
    }

    @Override
    public ClassSchedule getSchedule(int id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Schedule not found!"));
    }

    @Override
    public ClassSchedule updateSchedule(UpdateClassScheduleDTO dto) {
        ClassSchedule existing = scheduleRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Schedule not found!"));

        existing.setDate(dto.getDate());
        existing.setStartTime(dto.getStartTime());
        existing.setEndTime(dto.getEndTime());

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        existing.setRoom(room);

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        existing.setTeacher(teacher);

        existing.setStatus(dto.getStatus());

        return scheduleRepository.save(existing);
    }


    @Override
    public void deleteSchedule(int id) {
        scheduleRepository.deleteById(id);
    }
}
