package com.example.minor_project1.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Builder        // used to implement the builder pattern for the class. It provides a flexible way to create objects.
@AllArgsConstructor
@NoArgsConstructor  // used to create a no-argument constructor
@Entity             // used to mark a class as an entity which is mapped to a database table
public class Book {

    @Id                                     // used to mark a field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // used to auto-generate the primary key value
    private Integer id;
    private String name;

    @Enumerated(value = EnumType.STRING)    // This annotation is used to store enum as string in DB
    private Genre genre;

    private Boolean isAvailable;

    private Long issueCount;

    @CreationTimestamp      // automatically sets the creation timestamp when the entity is created
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    // the below two annotation is used for create a  foreign key

    @JoinColumn     // used to specify the foreign key column
    @ManyToOne      // many books can be issued by one student
//    private Student my_student;
    private Student student;

    @JoinColumn
    @ManyToOne
    private Author author;

}
