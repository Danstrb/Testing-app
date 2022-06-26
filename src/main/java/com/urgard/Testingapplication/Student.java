package com.urgard.Testingapplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    public Student (String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    @Id
    @SequenceGenerator (
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private String email;
    private Gender gender;
}
