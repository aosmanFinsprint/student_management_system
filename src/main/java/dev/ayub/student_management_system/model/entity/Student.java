package dev.ayub.student_management_system.model.entity;

import dev.ayub.student_management_system.model.enums.defination.GenderEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "firstName is required")
    private String firstName;

    @Column(name = "age")
    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private int age;

    @Column(name = "gender")
    @NotNull(message = "Gender is required")
    private GenderEnum gender;

    @Column(name = "score")
    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 100, message = "Score must not exceed 100")
    private int score;
}
