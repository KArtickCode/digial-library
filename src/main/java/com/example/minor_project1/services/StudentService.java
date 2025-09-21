package com.example.minor_project1.services;

import com.example.minor_project1.dtos.CreateStudentRequest;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.models.User;
import com.example.minor_project1.repositories.StudentCacheRepository;
import com.example.minor_project1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentCacheRepository studentCacheRepository;

    public Student create(CreateStudentRequest createStudentRequest){

        // Creating a student record / row in the student table
        Student student = createStudentRequest.convertTo();
        student = this.studentRepository.save(student);


        this.studentCacheRepository.create(student);

        User user = new User();
        user.setUsername(createStudentRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createStudentRequest.getPassword()));
        user.setAuthorities("admin");
        user.setSourceEntityId(student.getId());

        return student;

    }

    @Cacheable
    public Student findById(Integer studentId){

        Student student = this.studentCacheRepository.get(studentId);  // check in cache (Redis)
        if(student == null){
            student = this.studentRepository.findById(studentId).orElse(null);

            // TODO: Make this async because the client / FE doesn't need to wait for this operation to complete
            this.studentCacheRepository.create(student);  // As the cache is empty, we are adding the data to the cache for future cache hits
        }
        return student;
    }

    public void updateStudent(Integer studentId, Student student){
        //Either remove the data from the cache, so that next time, there will be a cache miss and latest data will be fetched from DB
        // or update the cache data in addition to updating the DB
        this.studentRepository.save(student);
    }
}