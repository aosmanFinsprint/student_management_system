package dev.ayub.student_management_system.service.user;

import dev.ayub.student_management_system.config.exceptions.UserNotFoundException;
import dev.ayub.student_management_system.model.dto.Request.user.CreateUserRequestDTO;
import dev.ayub.student_management_system.model.dto.Request.user.UserUpdateRequestDTO;
import dev.ayub.student_management_system.model.dto.Response.user.CreateUserResponseDTO;
import dev.ayub.student_management_system.model.entity.User;
import dev.ayub.student_management_system.model.enums.defination.StatusEnum;
import dev.ayub.student_management_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public  class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {

        Optional<User> existingUser = userRepository.findByEmail(requestDTO.getEmail());

        if (existingUser.isPresent()) {

            throw new UserNotFoundException("User already exist");
        }

        User newUser = User.builder()
                .firstName(requestDTO.getFirstName())
                .phone(requestDTO.getPhoneNumber())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .status(StatusEnum.INACTIVE)
                .build();

        User savedUser = userRepository.save(newUser);

        return CreateUserResponseDTO.builder()
                .firstName(savedUser.getFirstName())
                .email(savedUser.getEmail())
                .lastName(savedUser.getLastName())
                .phoneNumber(savedUser.getPhone())
                .status(savedUser.getStatus())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();

    }

    @Override
    public CreateUserResponseDTO updateUser(UserUpdateRequestDTO requestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(requestDTO.getEmail());

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist");
        }

        User user = existingUser.get();

        if (requestDTO.getFirstName() != null) {
            user.setFirstName(requestDTO.getFirstName());
        }

        if (requestDTO.getLastName() != null) {
            user.setLastName(requestDTO.getLastName());
        }

        if (requestDTO.getPhoneNumber() != null) {
            user.setPhone(requestDTO.getPhoneNumber());
        }


        User updatedUser = userRepository.save(user); //

        return CreateUserResponseDTO.builder()
                .firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName())
                .email(updatedUser.getEmail())
                .phoneNumber(updatedUser.getPhone())
                .status(updatedUser.getStatus())
                .createdAt(updatedUser.getCreatedAt())
                .updatedAt(updatedUser.getUpdatedAt())
                .build();
    }



}