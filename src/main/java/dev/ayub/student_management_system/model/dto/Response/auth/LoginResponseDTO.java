package dev.ayub.student_management_system.model.dto.Response.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private List<String> roles;
    private List<String> permissions;
    private String refreshToken;
    private Long expiresIn;
}

