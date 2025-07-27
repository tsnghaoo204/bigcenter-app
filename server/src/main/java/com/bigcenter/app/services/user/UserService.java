package com.bigcenter.app.services.user;

import com.bigcenter.app.dtos.requests.user.CreateUserDTO;
import com.bigcenter.app.dtos.requests.user.UpdateUserDTO;
import com.bigcenter.app.entities.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    String createUser(CreateUserDTO dto);
    Set<User> getAllUsers();
    User getUser(UUID id);
    User updateUser(UpdateUserDTO dto);
    void deleteUser(UUID id);
    List<User> searchUsers(String fullName, String email, String phone);

}
