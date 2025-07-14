package dev.ayub.student_management_system.Service;

import dev.ayub.student_management_system.dto.Request.CreateStudentRequestDTO;
import dev.ayub.student_management_system.dto.Response.CreateStudentResponseDTO;
import dev.ayub.student_management_system.model.entity.Student;
import dev.ayub.student_management_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO requestDTO) {
        Optional<Student> existingStudent = studentRepository.findByFirstName(requestDTO.getFirstName());

        if (existingStudent.isPresent()) {
            throw new RuntimeException("Student with first name '" + requestDTO.getFirstName() + "' already exists");
        }

        Student newStudent = Student.builder()
                .firstName(requestDTO.getFirstName())
                .age(requestDTO.getAge())
                .gender(requestDTO.getgender())
                .score(requestDTO.getscore())
                .build();

        Student savedStudent = studentRepository.save(newStudent);

        return CreateStudentResponseDTO.builder()
                .firstName(savedStudent.getFirstName())
                .age(savedStudent.getAge())
                .gender(savedStudent.getGender())
                .Score(savedStudent.getScore())
                .build();
    }
}
