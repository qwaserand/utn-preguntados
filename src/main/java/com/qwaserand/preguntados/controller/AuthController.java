package com.qwaserand.preguntados.controller;

import com.qwaserand.preguntados.dto.request.LoginRequest;
import com.qwaserand.preguntados.dto.request.RegisterRequest;
import com.qwaserand.preguntados.dto.response.AuthResponse;
import com.qwaserand.preguntados.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        AuthResponse authResponse = authService.register(request);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse res = authService.login(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
