package dev.ayub.student_management_system.repository;

import dev.ayub.student_management_system.model.enums.definaton.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dev.ayub.student_management_system.model.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
    List<User> findByStatus(StatusEnum status);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    @Query(" SELECT  u from  User u  where u.email =?1 ")
    User findUserByEmail(String email);
}
