package dev.ayub.student_management_system.controller;

import dev.ayub.student_management_system.model.entity.Student;
import dev.ayub.student_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")  //
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create a new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {  // n
        return studentRepository.save(student); //
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
