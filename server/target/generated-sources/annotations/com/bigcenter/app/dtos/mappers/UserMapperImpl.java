package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.user.CreateUserDTO;
import com.bigcenter.app.dtos.requests.user.UpdateUserDTO;
import com.bigcenter.app.dtos.responses.UserResponseDTO;
import com.bigcenter.app.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T19:20:14+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(CreateUserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFullName( dto.getFullName() );
        user.setEmail( dto.getEmail() );
        user.setEnable( dto.getEnable() );

        return user;
    }

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( user.getId() );
        userResponseDTO.setFullName( user.getFullName() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setEnable( user.getEnable() );
        userResponseDTO.setCreateAt( user.getCreateAt() );

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> toResponseDTOList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>( users.size() );
        for ( User user : users ) {
            list.add( toResponseDTO( user ) );
        }

        return list;
    }

    @Override
    public void updateEntity(UpdateUserDTO dto, User user) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            user.setId( dto.getId() );
        }
        if ( dto.getFullName() != null ) {
            user.setFullName( dto.getFullName() );
        }
        if ( dto.getPhone() != null ) {
            user.setPhone( dto.getPhone() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getEnable() != null ) {
            user.setEnable( dto.getEnable() );
        }
    }
}
