package com.bigcenter.app.services.user;

import com.bigcenter.app.dtos.requests.user.CreateUserDTO;
import com.bigcenter.app.dtos.requests.user.UpdateUserDTO;
import com.bigcenter.app.dtos.responses.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    String createUser(CreateUserDTO dto);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUser(UUID id);
    UserResponseDTO updateUser(UpdateUserDTO dto);
    void deleteUser(UUID id);
    List<UserResponseDTO> searchUsers(String fullName, String email, String phone);

}
