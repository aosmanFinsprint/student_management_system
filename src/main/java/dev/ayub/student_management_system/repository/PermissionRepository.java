package dev.ayub.student_management_system.repository;

import dev.ayub.student_management_system.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByStatus(int status);

}
