package com.bigcenter.app.auths;

import com.bigcenter.app.dtos.requests.EmailRequest;
import com.bigcenter.app.dtos.requests.TokenRequest;
import com.bigcenter.app.payloads.RegisterRequest;
import com.bigcenter.app.payloads.ConfirmRequest;
import com.bigcenter.app.payloads.LoginRequest;
import com.bigcenter.app.services.cognito.CognitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
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
    public String login(@RequestBody LoginRequest req) {
        String token = cognitoService.loginUser(req.getUsername(), req.getPassword());
        return token;
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
