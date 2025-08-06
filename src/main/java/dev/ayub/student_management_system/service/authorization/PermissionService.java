package dev.ayub.student_management_system.service.authorization;

import dev.ayub.student_management_system.model.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionService {
    Permission getPermissionById(Long id);

    List<Permission> getAllPermissions();


    Page<Permission> getAllPaginated(Pageable pageable);

    List<Permission> getPermissionsByStatus(int status);
}
