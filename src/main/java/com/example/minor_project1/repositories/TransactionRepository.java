package com.example.minor_project1.repositories;

import com.example.minor_project1.models.Book;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.models.Transaction;
import com.example.minor_project1.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // select t from Transaction t where t.book.id = ?1 and t.student.id = ?1 and t.transactionType = ISSUE
    // and t.transactionStatus = SUCCESS order by id desc limit 1

    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType transactionType);
}