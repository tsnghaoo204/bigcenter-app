package com.bigcenter.app.controllers.api;

import com.bigcenter.app.dtos.requests.user.CreateUserDTO;
import com.bigcenter.app.dtos.requests.user.UpdateUserDTO;
import com.bigcenter.app.dtos.responses.UserResponseDTO;
import com.bigcenter.app.payloads.request.RoleRequest;
import com.bigcenter.app.services.cognito.CognitoService;
import com.bigcenter.app.services.user.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CognitoService cognitoService;

    // ✅ Create user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    // ✅ Get all users with pagination support (React Admin compatible)
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(
            @RequestParam(name = "_start", defaultValue = "0") int start,
            @RequestParam(name = "_end", defaultValue = "10") int end
    ) {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        int total = allUsers.size();

        int toIndex = Math.min(end, total);
        List<UserResponseDTO> page = allUsers.subList(start, toIndex);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "users " + start + "-" + (toIndex - 1) + "/" + total);
        headers.add("Access-Control-Expose-Headers", "Content-Range");

        return ResponseEntity.ok().headers(headers).body(page);
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    @PermitAll
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    // ✅ Update user
    @PutMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    // ✅ Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{username}/role")
    public ResponseEntity<?> updateRole(@PathVariable String username, @RequestBody RoleRequest request) {
        try {
            cognitoService.changeUserRole(username, request.getRole());
            return ResponseEntity.ok("Role updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating role: " + e.getMessage());
        }
    }
}
