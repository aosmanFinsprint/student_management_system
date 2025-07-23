package dev.ayub.student_management_system.model.dto.Request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
