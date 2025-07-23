package dev.ayub.student_management_system.Service.user;


import dev.ayub.student_management_system.model.dto.Request.user.CreateUserRequestDTO;
import dev.ayub.student_management_system.model.dto.Response.user.CreateUserResponseDTO;
import dev.ayub.student_management_system.model.entity.User;
import dev.ayub.student_management_system.model.enums.definaton.StatusEnum;
import dev.ayub.student_management_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${password.setup.url}")
    private String setupBaseUrl;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {


        // Create the user entity
        User user = User.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhoneNumber())
                .password(null)
                .status(StatusEnum.INACTIVE)
                .build();

        // Save the user to the database
        user = userRepository.save(user);

        // Generate a password setup token
        String token = generatePasswordSetupToken(user);
        user.setResetToken(token);
        userRepository.save(user);

        // Build the password setup link
        String setupLink = setupBaseUrl + "?token=" + token;

        // Return the response DTO
        return CreateUserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhone())
                .createdAt(LocalDateTime.now().toString())
                .status(user.getStatus())
                .build();
    }

    @Override
    public void setPassword(String token, String newPassword) {
        // Decode and validate the token
        Optional<User> optionalUser = userRepository.findByResetToken(token);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setStatus(StatusEnum.ACTIVE); // Activate the user after setting the password
        user.setResetToken(null); // Clear the token after successful password setup
        userRepository.save(user);
    }

    // Method to generate the password setup token
    private String generatePasswordSetupToken(User user) {
        return UUID.randomUUID().toString(); // Simple UUID for demonstration
    }

    // Method to build the password setup email body
    private String buildPasswordSetupEmail(String firstName, String setupLink) {
        return "Hello " + firstName + ",\n\n" +
                "Please set up your password using the following link:\n" +
                setupLink + "\n\n" +
                "Best regards,\nThe Team";
    }


    @Override
    public void forgotPassword(String email) {
        // Find the user by email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with the provided email does not exist");
        }

        User user = optionalUser.get();

        // Generate a password reset token
        String token = generatePasswordSetupToken(user);

        // Save the reset token to the user and update the database
        user.setResetToken(token);
        userRepository.save(user);

        // Build the password reset link
        String resetLink = setupBaseUrl + "?token=" + token;

        // Send the reset password email

    }

    @Override
    public CreateUserResponseDTO getUserProfile(User user) {

        return CreateUserResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhone())
                .status(user.getStatus())
                .build();
    }

    // Method to build the password reset email body
    private String buildPasswordResetEmail(String firstName, String resetLink) {
        return "Hello " + firstName + ",\n\n" +
                "You requested to reset your password. Please use the following link to reset it:\n" +
                resetLink + "\n\n" +
                "If you did not request this, please ignore this email.\n\n" +
                "Best regards,\nThe Team";
    }

}

