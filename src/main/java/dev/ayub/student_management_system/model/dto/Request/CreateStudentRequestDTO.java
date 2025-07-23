package dev.ayub.student_management_system.model.dto.Request;

import dev.ayub.student_management_system.model.enums.definaton.GenderEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateStudentRequestDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    @NotNull(message = "Gender is required")
    private GenderEnum gender;

    @Min(value = 0)
    @Max(value = 100)
    private int score;

    public String getFirstName(){
        return this.firstName = firstName;
    }

    public int getAge(){
        return this.age = age;
    }

    public GenderEnum getgender(){
        return this.gender = gender;
    }

    public int getscore(){
        return this.score = score;
    }
}
