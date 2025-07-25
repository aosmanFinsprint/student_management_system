package dev.ayub.student_management_system.model.dto.Response;

import lombok.*;

@Data
@NoArgsConstructor
public class AuthenticationResponseDTO {
    private String token;

    public AuthenticationResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}