package com.bigcenter.app.services.classes;

import com.bigcenter.app.dtos.mappers.ClassMapper;
import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.payloads.exceptions.ResourceNotFoundException;
import com.bigcenter.app.repositories.ClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
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
    @Transactional(readOnly = true)
    public List<ClassResponseDTO> getAllClass() {
            List<Class> classes = classRepository.findAll();

            return classes.stream()
                    .map(classMapper::toResponseDTO)
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO getClass(String className) {
        Class cl = classRepository.findByName(className)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found!"));
        return classMapper.toResponseDTO(cl);
    }

    @Override
    public ClassResponseDTO updateClass(UpdateClassDTO dto) {
        Class existing = classRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found!"));

        existing.setName(dto.getName());
        existing.setEndDate(dto.getEndDate());
        existing.setStartDate(dto.getStartDate());

        Class saved = classRepository.save(existing);
        return classMapper.toResponseDTO(saved);
    }

    @Override
    public void deleteClass(UUID id) {
        if (!classRepository.existsById(id)) {
            throw new ResourceNotFoundException("Class not found!");
        }
        classRepository.deleteById(id);
    }
}