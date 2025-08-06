package dev.ayub.student_management_system.repository;

import dev.ayub.student_management_system.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Long>{

}
