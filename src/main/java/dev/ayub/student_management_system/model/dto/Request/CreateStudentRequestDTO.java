package dev.ayub.student_management_system.dto.Request;

import dev.ayub.student_management_system.model.enums.definaton.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateStudentRequestDTO {
    private String firstName;
    private int age;
    private GenderEnum gender;
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
