package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.class_schedule.CreateClassScheduleDTO;
import com.bigcenter.app.dtos.requests.class_schedule.UpdateClassScheduleDTO;
import com.bigcenter.app.dtos.responses.ClassScheduleResponseDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.ClassSchedule;
import com.bigcenter.app.entities.Room;
import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T00:51:41+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class ClassScheduleMapperImpl implements ClassScheduleMapper {

    @Override
    public ClassSchedule toEntity(CreateClassScheduleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ClassSchedule classSchedule = new ClassSchedule();

        classSchedule.setClassField( createClassScheduleDTOToClass( dto ) );
        classSchedule.setRoom( createClassScheduleDTOToRoom( dto ) );
        classSchedule.setTeacher( createClassScheduleDTOToTeacher( dto ) );
        classSchedule.setDate( dto.getDate() );
        classSchedule.setStartTime( dto.getStartTime() );
        classSchedule.setEndTime( dto.getEndTime() );
        classSchedule.setStatus( dto.getStatus() );

        return classSchedule;
    }

    @Override
    public ClassScheduleResponseDTO toResponseDTO(ClassSchedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ClassScheduleResponseDTO classScheduleResponseDTO = new ClassScheduleResponseDTO();

        classScheduleResponseDTO.setClassId( scheduleClassFieldId( schedule ) );
        classScheduleResponseDTO.setClassName( scheduleClassFieldName( schedule ) );
        classScheduleResponseDTO.setRoomId( scheduleRoomId( schedule ) );
        classScheduleResponseDTO.setRoomName( scheduleRoomName( schedule ) );
        classScheduleResponseDTO.setTeacherId( scheduleTeacherId( schedule ) );
        classScheduleResponseDTO.setTeacherName( scheduleTeacherUserFullName( schedule ) );
        classScheduleResponseDTO.setId( schedule.getId() );
        classScheduleResponseDTO.setDate( schedule.getDate() );
        classScheduleResponseDTO.setStartTime( schedule.getStartTime() );
        classScheduleResponseDTO.setEndTime( schedule.getEndTime() );
        classScheduleResponseDTO.setStatus( schedule.getStatus() );

        return classScheduleResponseDTO;
    }

    @Override
    public List<ClassScheduleResponseDTO> toResponseDTOList(List<ClassSchedule> list) {
        if ( list == null ) {
            return null;
        }

        List<ClassScheduleResponseDTO> list1 = new ArrayList<ClassScheduleResponseDTO>( list.size() );
        for ( ClassSchedule classSchedule : list ) {
            list1.add( toResponseDTO( classSchedule ) );
        }

        return list1;
    }

    @Override
    public void updateEntity(UpdateClassScheduleDTO dto, ClassSchedule schedule) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            schedule.setId( dto.getId() );
        }
        if ( dto.getDate() != null ) {
            schedule.setDate( dto.getDate() );
        }
        if ( dto.getStartTime() != null ) {
            schedule.setStartTime( dto.getStartTime() );
        }
        if ( dto.getEndTime() != null ) {
            schedule.setEndTime( dto.getEndTime() );
        }
        if ( dto.getStatus() != null ) {
            schedule.setStatus( dto.getStatus() );
        }
    }

    protected Class createClassScheduleDTOToClass(CreateClassScheduleDTO createClassScheduleDTO) {
        if ( createClassScheduleDTO == null ) {
            return null;
        }

        Class class1 = new Class();

        class1.setId( createClassScheduleDTO.getClassId() );

        return class1;
    }

    protected Room createClassScheduleDTOToRoom(CreateClassScheduleDTO createClassScheduleDTO) {
        if ( createClassScheduleDTO == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( createClassScheduleDTO.getRoomId() );

        return room;
    }

    protected Teacher createClassScheduleDTOToTeacher(CreateClassScheduleDTO createClassScheduleDTO) {
        if ( createClassScheduleDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setId( createClassScheduleDTO.getTeacherId() );

        return teacher;
    }

    private UUID scheduleClassFieldId(ClassSchedule classSchedule) {
        if ( classSchedule == null ) {
            return null;
        }
        Class classField = classSchedule.getClassField();
        if ( classField == null ) {
            return null;
        }
        UUID id = classField.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String scheduleClassFieldName(ClassSchedule classSchedule) {
        if ( classSchedule == null ) {
            return null;
        }
        Class classField = classSchedule.getClassField();
        if ( classField == null ) {
            return null;
        }
        String name = classField.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private UUID scheduleRoomId(ClassSchedule classSchedule) {
        if ( classSchedule == null ) {
            return null;
        }
        Room room = classSchedule.getRoom();
        if ( room == null ) {
            return null;
        }
        UUID id = room.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String scheduleRoomName(ClassSchedule classSchedule) {
        if ( classSchedule == null ) {
            return null;
        }
        Room room = classSchedule.getRoom();
        if ( room == null ) {
            return null;
        }
        String name = room.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private UUID scheduleTeacherId(ClassSchedule classSchedule) {
        if ( classSchedule == null ) {
            return null;
        }
        Teacher teacher = classSchedule.getTeacher();
        if ( teacher == null ) {
            return null;
        }
        UUID id = teacher.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String scheduleTeacherUserFullName(ClassSchedule classSchedule) {
        if ( classSchedule == null ) {
            return null;
        }
        Teacher teacher = classSchedule.getTeacher();
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        String fullName = user.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }
}
