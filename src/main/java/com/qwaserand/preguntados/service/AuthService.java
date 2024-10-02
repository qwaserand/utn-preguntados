package com.qwaserand.preguntados.service;

import com.qwaserand.preguntados.dto.request.LoginRequest;
import com.qwaserand.preguntados.dto.request.RegisterRequest;
import com.qwaserand.preguntados.dto.response.AuthResponse;
import com.qwaserand.preguntados.entity.Role;
import com.qwaserand.preguntados.entity.User;
import com.qwaserand.preguntados.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     *
     * @param request
     * @return
     */
    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        AuthResponse authResponse = AuthResponse.builder() //
                .token(jwtService.getToken(user)) //
                .build();

        return authResponse;

    }

    /**
     *
     * @param request
     * @return
     */
    public AuthResponse login(LoginRequest request) {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.getToken(user);

        AuthResponse authResponse = AuthResponse.builder() //
                .token(token) //
                .build();

        return authResponse;

    }

}
