package dev.ayub.student_management_system.service.authorization.impl;

import dev.ayub.student_management_system.config.exceptions.RecordNotFoundException;
import dev.ayub.student_management_system.model.dto.Request.authorization.CreateRoleRequestDTO;
import dev.ayub.student_management_system.model.dto.Response.authorization.CreateRoleResponseDTO;
import dev.ayub.student_management_system.model.dto.Response.authorization.PermissionResponseDTO;
import dev.ayub.student_management_system.model.entity.Permission;
import dev.ayub.student_management_system.model.entity.Role;
import dev.ayub.student_management_system.repository.PermissionRepository;
import dev.ayub.student_management_system.repository.RoleRepository;
import dev.ayub.student_management_system.service.authorization.RoleService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Builder
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    @Transactional
    public CreateRoleResponseDTO createRole(CreateRoleRequestDTO request) {
        // Convert List to Set using collector or new HashSet
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(request.getPermissionIds()));

        Role role = Role.builder()
                .name(request.getName())
                .permissions(permissions)
                .build();

        Role savedRole = roleRepository.save(role);

        return CreateRoleResponseDTO.builder()
                .id(savedRole.getId())
                .name(savedRole.getName())
                .permissions(permissions.stream()
                        .map(permission -> PermissionResponseDTO.builder()
                                .id(permission.getId())
                                .name(permission.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Transactional
    @Override
    public CreateRoleResponseDTO updateRole(Long id, CreateRoleRequestDTO request) {
        // Find existing role
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Role not found with id: " + id));

        // Get new permissions
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(request.getPermissionIds()));

        // Update role
        existingRole.setName(request.getName());
        existingRole.setPermissions(permissions);

        Role savedRole = roleRepository.save(existingRole);

        return CreateRoleResponseDTO.builder()
                .id(savedRole.getId())
                .name(savedRole.getName())
                .permissions(permissions.stream()
                        .map(permission -> PermissionResponseDTO.builder()
                                .id(permission.getId())
                                .name(permission.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Role not found with id: " + id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> getAllPaginatedRoles(Pageable pageable){
        return roleRepository.findAll(pageable);
    }
}
