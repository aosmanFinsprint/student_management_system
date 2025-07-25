package dev.ayub.student_management_system.model.dto.Response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.ayub.student_management_system.model.enums.definaton.StatusEnum;
import lombok.*;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Instant createdAt;
    private Instant updatedAt;
    private StatusEnum status;
}

