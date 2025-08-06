package dev.ayub.student_management_system.service.authorization.impl;

import dev.ayub.student_management_system.config.exceptions.RecordNotFoundException;
import dev.ayub.student_management_system.model.entity.Permission;
import dev.ayub.student_management_system.repository.PermissionRepository;
import dev.ayub.student_management_system.service.authorization.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Permission not found with id: " + id));
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Page<Permission> getAllPaginated(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    @Override
    public List<Permission> getPermissionsByStatus(int status) {
        return permissionRepository.findByStatus(status);
    }
}
