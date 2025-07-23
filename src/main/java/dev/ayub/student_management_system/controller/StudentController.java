package dev.ayub.student_management_system.controller;

import dev.ayub.student_management_system.Service.StudentService;
import dev.ayub.student_management_system.model.dto.Request.CreateStudentRequestDTO;
import dev.ayub.student_management_system.model.entity.Student;
import dev.ayub.student_management_system.repository.StudentRepository;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")  //
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    // Create a new student
    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentRequestDTO requestDTO) {
        return ResponseEntity.ok(studentService.createStudent(requestDTO));
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll(); //
    }

    //  Get one student by ID
    @GetMapping("/{id}")  //
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id); //

        return optionalStudent
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
