package dev.ayub.student_management_system.controller;

import dev.ayub.student_management_system.config.Securty.JWTService;
import dev.ayub.student_management_system.model.dto.Request.AuthenticationRequestDTO;
import dev.ayub.student_management_system.model.dto.Request.user.CreateUserRequestDTO;
import dev.ayub.student_management_system.model.dto.Response.AuthenticationResponseDTO;
import dev.ayub.student_management_system.model.entity.User;
import dev.ayub.student_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {



        private final AuthenticationManager authenticationManager;
        private final UserRepository userRepository;
        private final JWTService jwtService;
        private final PasswordEncoder passwordEncoder;

//        @PostMapping("/login")
//        public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO request) {
//
//        }

        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody AuthenticationRequestDTO request) {

            return ResponseEntity.ok("User registered successfully");
        }
}


