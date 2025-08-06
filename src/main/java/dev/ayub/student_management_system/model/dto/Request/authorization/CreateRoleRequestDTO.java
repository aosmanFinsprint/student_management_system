package dev.ayub.student_management_system.model.dto.Request.authorization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequestDTO {
    @NotBlank(message = "Role name is required")
    private String name;

    @NotEmpty(message = "At least one permission must be selected")
    private Set<Long> permissionIds;

}
