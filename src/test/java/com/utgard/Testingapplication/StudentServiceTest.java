package com.utgard.Testingapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void itShouldGetListOfAllStudents() {
        // given
        underTest.addStudent(new Student("John", "john@gmail.com", Gender.MALE));

        // when
        underTest.getAllStudents();

        // then
        verify(studentRepository).findAll();
    }

    @Test
    void itShouldGetAnEmptyListOfStudents() {
        // given
        // when
        underTest.getAllStudents();

        // then
        verify(studentRepository).findAll();
    }


    @Test
    void itShouldAddStudent() {
        // given
        var student = new Student("John", "john@gmail.com", Gender.MALE);

        // when
        underTest.addStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void itShouldThrowWhenEmailIsTaken() {
        // given
        var student = new Student("John", "jo@gmail.com", Gender.MALE);

        given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);

        // when
        // then
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email " + student.getEmail() + "already taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void itShouldThrowWhenStudentToBeDeletedIsMissing() {
        // given
        var student = new Student("John", "john@gmail.com", Gender.MALE);
        given(studentRepository.existsById(student.getId())).willReturn(false);

        // when
        // then
        assertThatThrownBy(() -> underTest.deleteStudent(student.getId())).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("Student with id " + student.getId() + " does not exists");

        verify(studentRepository, never()).delete(student);
    }

    @Test
    void deleteStudent() {
        // given
        var student = new Student(1L,"John", "john@gmail.com", Gender.MALE);
        given(studentRepository.existsById(student.getId())).willReturn(true);

        // when
        // then
        underTest.deleteStudent(student.getId());
        verify(studentRepository).deleteById(student.getId());
    }
}