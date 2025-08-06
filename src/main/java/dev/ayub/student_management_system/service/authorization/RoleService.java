package dev.ayub.student_management_system.service.authorization;


import dev.ayub.student_management_system.model.entity.Role;
import dev.ayub.student_management_system.model.dto.Request.authorization.CreateRoleRequestDTO;
import dev.ayub.student_management_system.model.dto.Response.authorization.CreateRoleResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    CreateRoleResponseDTO createRole(CreateRoleRequestDTO request);

    @Transactional
    CreateRoleResponseDTO updateRole(Long id, CreateRoleRequestDTO request);

    Role getRoleById(Long id);
    List<Role> getAllRoles();

    Page<Role> getAllPaginatedRoles(Pageable pageable);
}
