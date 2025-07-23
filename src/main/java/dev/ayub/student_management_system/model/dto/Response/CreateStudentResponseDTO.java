package dev.ayub.student_management_system.model.dto.Response;

import dev.ayub.student_management_system.model.enums.definaton.GenderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateStudentResponseDTO {
    private String firstName;
    private int age;
    private GenderEnum gender;
    private int Score;
}
