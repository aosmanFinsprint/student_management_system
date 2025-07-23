package dev.ayub.student_management_system.model.dto.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
