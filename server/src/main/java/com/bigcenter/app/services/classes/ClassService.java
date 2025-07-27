package com.bigcenter.app.services.classes;

import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.entities.Class;

import java.util.Set;
import java.util.UUID;

public interface ClassService {
    String createClass(CreateClassDTO dto);
    Set<Class> getAllClass();
    Class getClass(String className);
    Class updateClass(UpdateClassDTO dto);
    void deleteClass(UUID id);
}
