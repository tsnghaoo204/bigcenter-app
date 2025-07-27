package com.bigcenter.app.services.user;

import com.bigcenter.app.dtos.mappers.UserMapper;
import com.bigcenter.app.dtos.requests.user.CreateUserDTO;
import com.bigcenter.app.dtos.requests.user.UpdateUserDTO;
import com.bigcenter.app.entities.User;
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
    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public User updateUser(UpdateUserDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setEnable(dto.getEnable());
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUsers(String fullName, String email, String phone) {
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


        return userRepository.findAll(spec);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
