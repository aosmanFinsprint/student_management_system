package dev.ayub.student_management_system.model.dto.Response.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseDTO {
    private Long id;
    private String name;
    private String groupName;

}
