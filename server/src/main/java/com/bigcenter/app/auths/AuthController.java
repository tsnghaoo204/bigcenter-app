package com.bigcenter.app.auths;

import com.bigcenter.app.dtos.requests.EmailRequest;
import com.bigcenter.app.dtos.requests.TokenRequest;
import com.bigcenter.app.payloads.request.RegisterRequest;
import com.bigcenter.app.payloads.request.ConfirmRequest;
import com.bigcenter.app.payloads.request.LoginRequest;
import com.bigcenter.app.services.cognito.CognitoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private CognitoService cognitoService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        cognitoService.registerUser(req.getEmail(), req.getPassword(), req.getPhone(), req.getFullname());
        return "OTP sent to email or phone";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestBody ConfirmRequest req) {
        cognitoService.confirmUser(req.email, req.code);
        return "User confirmed!";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest req) {
        Map<String, Object> token = cognitoService.loginUser(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/resend-code")
    public String resend(@RequestBody EmailRequest email) {
        cognitoService.resendCode(email.getEmail());
        return "OTP sent to email or phone";
    }

    @PostMapping("/logout")
    public String logout(@RequestBody TokenRequest token) {
        cognitoService.logout(token.getToken());
        return "Logged out successfully";
    }
}
