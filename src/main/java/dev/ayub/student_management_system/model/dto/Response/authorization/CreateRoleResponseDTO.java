package dev.ayub.student_management_system.model.dto.Response.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleResponseDTO {
    private Long id;
    private String name;
    private Set<PermissionResponseDTO> permissions;
    private int status;
    private String remarks;

}
