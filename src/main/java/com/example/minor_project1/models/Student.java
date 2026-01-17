package com.example.minor_project1.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

//    private String id = UUID.randomUUID().toString();    // UUID is to give the unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Department department;

    @Column(unique = true, nullable = false)    // only @Column has no effect It tells JPA how to map the Java field to a database column with specific properties.@Column(name = "roll_number", unique = true, nullable = false, length = 50)
    private String rollNumber;       // cse/22/117 this type

    @Column(unique = true, nullable = false)
    private String email;

    private String country;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

//    // thia is create a bi-directional relation between book and student
//    @OneToMany(mappedBy = "my_student")     // This (mappedBy = ) is use for back reference
//    private List<Book> bookList;


    // Books assigned to the student

}
