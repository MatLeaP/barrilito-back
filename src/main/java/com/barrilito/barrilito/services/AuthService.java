package com.barrilito.barrilito.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.barrilito.barrilito.DTO.AuthResponse;
import com.barrilito.barrilito.DTO.LoginRequest;
import com.barrilito.barrilito.DTO.RegisterRequest;
import com.barrilito.barrilito.model.RoleEntity;
import com.barrilito.barrilito.model.UserEntity;
import com.barrilito.barrilito.repository.I_UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final I_UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final  PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserDetails user = userRepository.findByUserName(request.getUserName()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request){
        UserEntity user = UserEntity.builder()
            .userName(request.getUserName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .country(request.getCountry())
            .role(RoleEntity.USER)
            .build();

            userRepository.save(user);

            return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
