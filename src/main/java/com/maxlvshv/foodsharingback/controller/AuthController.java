package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.JwtAuthenticationResponse;
import com.maxlvshv.foodsharingback.dto.SignInRequest;
import com.maxlvshv.foodsharingback.dto.SignUpRequest;
import com.maxlvshv.foodsharingback.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @Operation(summary = "Регистрация админа")
    @PostMapping("/sign-up-admin")
    public ResponseEntity<JwtAuthenticationResponse> signUpAdmin(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUpAdmin(request));
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
