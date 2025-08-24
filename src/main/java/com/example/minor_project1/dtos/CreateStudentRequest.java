package com.example.minor_project1.dtos;

import com.example.minor_project1.models.Department;
import com.example.minor_project1.models.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {

    @NotBlank
    private String name;

    @NotNull
    private Department department;

    @NotBlank
    private String rollNumber;

    @NotBlank
    @Email
    private String email;

    private String country;

    public Student convertTo(){

        Student student = new Student();
        student.setName(this.name);
        student.setDepartment(this.department);
        student.setEmail(this.email);
        student.setRollNumber(this.rollNumber);
        student.setCountry(this.country);

        return student;
    }

}
