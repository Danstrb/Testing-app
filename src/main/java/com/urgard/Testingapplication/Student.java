package com.urgard.Testingapplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank //Validation (for controller) => not blank, not null
    private String name;
    @Email //Validation (for controller) => is email
    private String email;
    @NotNull //Validation
    private Gender gender;
}
