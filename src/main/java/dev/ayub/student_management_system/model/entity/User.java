package dev.ayub.student_management_system.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.ayub.student_management_system.model.enums.converters.StatusEnumConverter;
import dev.ayub.student_management_system.model.enums.defination.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @JsonBackReference
    @Column(name = "password")
    private String password;

    @Column(name = "reset_token", nullable = true)
    private String resetToken;

    @Convert(converter = StatusEnumConverter.class)
    @Column(name = "status", columnDefinition = "integer default 0")
    private StatusEnum status;

    @Column(name = "reset_token_expiry_date")
    private LocalDateTime resetTokenExpiryDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    public String getFullName() {
        return firstName+" "+lastName;
    }


}
