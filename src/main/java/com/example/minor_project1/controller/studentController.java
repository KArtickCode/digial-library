package com.example.minor_project1.controller;

import com.example.minor_project1.dtos.CreateStudentRequest;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.models.User;
import com.example.minor_project1.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class studentController {

    @Autowired
    StudentService studentService;

    @PostMapping("")             // Anyone can create a student
    public Student createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){

        return studentService.create(createStudentRequest);
    }

    @GetMapping("/{studentId}")                // Student and libraria both can see the student details
    public Student getStudentDetails(@PathVariable("studentId") Integer studentId){
        //student service class method to retrieve the student details;
        return studentService.findById(studentId);
    }

    @GetMapping("/details")                // Only Student can see their own details because here studentId is not present in the librarian(librarian has a librarianId)
    public Student getStudentDetails2(){
//        Integer studentId = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
//        Integer userId = user.getId();

        //student service class method to retrieve the student details;
        // Receive studentId from security context
        return studentService.findById(user.getSourceEntityId());
    }
    

}
