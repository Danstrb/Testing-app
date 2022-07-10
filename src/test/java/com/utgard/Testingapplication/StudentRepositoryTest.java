package com.utgard.Testingapplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // given
        String email = "john@john.com";
        Student student = new Student(
                "John",
                email,
                Gender.MALE
        );
        underTest.save(student);

        // when
        boolean exists = underTest.selectExistsEmail(email);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {
        // given
        String email = "robert@gmail.com";

        // when
        boolean exists = underTest.selectExistsEmail(email);

        // then
        assertThat(exists).isFalse();
    }
}