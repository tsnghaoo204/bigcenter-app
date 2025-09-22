package com.bigcenter.app.services.user;

import com.bigcenter.app.dtos.mappers.UserMapper;
import com.bigcenter.app.dtos.requests.user.CreateUserDTO;
import com.bigcenter.app.dtos.requests.user.UpdateUserDTO;
import com.bigcenter.app.dtos.responses.UserResponseDTO;
import com.bigcenter.app.entities.User;
import com.bigcenter.app.payloads.exceptions.ResourceNotFoundException;
import com.bigcenter.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public String createUser(CreateUserDTO dto) {
        userRepository.save(userMapper.toEntity(dto));
        return "User created!";
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userMapper.toResponseDTOList(userRepository.findAll());
    }

    @Override
    public UserResponseDTO getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(UpdateUserDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setEnable(dto.getEnable());
        userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> searchUsers(String fullName, String email, String phone) {
        Specification<User> spec = (root, query, cb) -> cb.conjunction();

        if (fullName != null && !fullName.isEmpty()) {
            spec = spec.and(UserSpecification.hasFullName(fullName));
        }
        if (email != null && !email.isEmpty()) {
            spec = spec.and(UserSpecification.hasEmail(email));
        }
        if (phone != null && !phone.isEmpty()) {
            spec = spec.and(UserSpecification.hasPhone(phone));
        }


        return userMapper.toResponseDTOList(userRepository.findAll(spec));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
