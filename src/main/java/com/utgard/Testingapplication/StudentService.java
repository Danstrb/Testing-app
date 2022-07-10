package com.utgard.Testingapplication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        var emailExists = studentRepository.selectExistsEmail(student.getEmail());
        if (emailExists)
            throw new IllegalArgumentException("Email " + student.getEmail() + "already taken");
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        var studentExists = studentRepository.existsById(id);
        if (!studentExists)
            throw new IllegalArgumentException("Student with id " + id + " does not exists");
        studentRepository.deleteById(id);
    }
}
