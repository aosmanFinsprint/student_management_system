package dev.ayub.student_management_system.model.dto.Request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    public String email;

    @NotBlank
    public String password;
}

