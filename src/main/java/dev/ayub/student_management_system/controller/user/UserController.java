package dev.ayub.student_management_system.controller.user;

import dev.ayub.student_management_system.Service.user.UserService;
import dev.ayub.student_management_system.config.Utils.ApiResponseUtil;
import dev.ayub.student_management_system.model.dto.Request.user.CreateUserRequestDTO;
import dev.ayub.student_management_system.model.dto.Request.user.UserUpdateRequestDTO;
import dev.ayub.student_management_system.model.dto.Response.user.CreateUserResponseDTO;
import dev.ayub.student_management_system.model.dto.SuccessResponseDTO;
import dev.ayub.student_management_system.model.entity.User;
import dev.ayub.student_management_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<SuccessResponseDTO<CreateUserResponseDTO>> createUser(@RequestBody CreateUserRequestDTO requestDTO) {
        CreateUserResponseDTO createdUser = userService.createUser(requestDTO);
        return ApiResponseUtil.success(createdUser, "User created successfully!");
    }

    @PutMapping
    public ResponseEntity<CreateUserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.updateUser(requestDTO));
    }

//    @PostMapping("/set-password")
//    public ResponseEntity<SuccessResponseDTO<String>> setPassword(@RequestParam String token, @RequestParam String newPassword) {
//        userService.setPassword(token, newPassword);
//        return ApiResponseUtil.success(null, "Password set successfully!");
//    }
//
//    @PostMapping("/forgot-password")
//    public ResponseEntity<SuccessResponseDTO<String>> forgotPassword(@RequestParam String email) {
//        userService.forgotPassword(email);
//        return ApiResponseUtil.success(null, "Password reset instructions sent to your email!");
//    }
}
