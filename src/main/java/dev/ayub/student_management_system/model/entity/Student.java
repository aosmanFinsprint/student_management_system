package dev.ayub.student_management_system.model.entity;

import dev.ayub.student_management_system.model.enums.definaton.GenderEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Data
//@NoArgsConstructor
@Builder
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "score")
    private int score;
}
