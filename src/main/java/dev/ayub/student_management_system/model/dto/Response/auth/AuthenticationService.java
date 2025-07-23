package dev.ayub.student_management_system.model.dto.Response.auth;


import studio.casper.oh_angels.model.dto.request.auth.LoginRequestDTO;
import studio.casper.oh_angels.model.dto.response.auth.LoginResponseDTO;

public interface AuthenticationService {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
    LoginResponseDTO refreshToken(String refreshToken);
    void logout(String token);
}
