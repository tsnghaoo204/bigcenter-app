package com.bigcenter.app.services.classes;

import com.bigcenter.app.dtos.mappers.ClassMapper;
import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.repositories.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<ClassResponseDTO> getAllClass() {
        return classMapper.toResponseDTOList(classRepository.findAll());
    }

    @Override
    public ClassResponseDTO getClass(String className) {
        Class cl = classRepository.findByName(className)
                .orElseThrow(() -> new NoSuchElementException("Class not found!"));
        return classMapper.toResponseDTO(cl);
    }

    @Override
    public ClassResponseDTO updateClass(UpdateClassDTO dto) {
        Class existing = classRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Class not found!"));

        existing.setName(dto.getName());
        existing.setEndDate(dto.getEndDate());
        existing.setStartDate(dto.getStartDate());
        existing.setSubject(dto.getSubject());
        classRepository.save(existing);
        return classMapper.toResponseDTO(existing);
    }

    @Override
    public void deleteClass(UUID id) {
        classRepository.deleteById(id);
    }
}
