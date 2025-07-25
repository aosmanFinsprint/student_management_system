package dev.ayub.student_management_system.Service.user;


import dev.ayub.student_management_system.model.dto.Request.AuthenticationRequestDTO;
import dev.ayub.student_management_system.model.dto.Request.user.CreateUserRequestDTO;
import dev.ayub.student_management_system.model.dto.Request.user.UserUpdateRequestDTO;
import dev.ayub.student_management_system.model.dto.Response.user.CreateUserResponseDTO;
import dev.ayub.student_management_system.model.entity.User;

public interface UserService {
    CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO);

    CreateUserResponseDTO updateUser(UserUpdateRequestDTO requestDTO);

//    void setPassword(String token, String newPassword);
//
//    void forgotPassword(String email);
//
//    CreateUserResponseDTO getUserProfile(User user);
//
//    void register(AuthenticationRequestDTO request);
}

