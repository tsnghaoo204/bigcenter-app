package com.bigcenter.app.services.classes;

import com.bigcenter.app.dtos.mappers.ClassMapper;
import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.repositories.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    @Override
    public String createClass(CreateClassDTO dto) {
        if(classRepository.existsByName(dto.getName())){
            throw new RuntimeException("Class name already exists");
        }
        classRepository.save(classMapper.toEntity(dto));
        return "Created class successfully!";
    }

    @Override
    public Set<Class> getAllClass() {
        return new HashSet<>(classRepository.findAll());
    }

    @Override
    public Class getClass(String className) {
        return classRepository.findByName(className)
                .orElseThrow(() -> new NoSuchElementException("Class not found!"));
    }

    @Override
    public Class updateClass(UpdateClassDTO dto) {
        Class existing = classRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Class not found!"));

        existing.setName(dto.getName());
        existing.setEndDate(dto.getEndDate());
        existing.setStartDate(dto.getStartDate());
        existing.setSubject(dto.getSubject());
        return classRepository.save(existing);
    }

    @Override
    public void deleteClass(UUID id) {
        classRepository.deleteById(id);
    }
}
