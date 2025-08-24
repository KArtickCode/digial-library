package com.example.minor_project1.services;

import com.example.minor_project1.models.*;
import com.example.minor_project1.repositories.TransactionRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Value("${books.issue.max-allowed}")
    Integer maxAllowedBooks;

    @Value("${books.issue.duration}")
    Integer issuanceDaysThreshold;

    @Value("${books.fine.per-day}")
    Integer finePerDay;

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TransactionRepository transactionRepository;

    public String issueTxn(Integer studentId, Integer bookId){

        /**
         * 1. book id should be valid and is available
         * 2. student id should be valid number of books available to that student should be less than maxAllowed
         *
         */

        Book book = bookService.findById(bookId);
        Long originalIssueCount = book.getIssueCount() == null ? 0 : book.getIssueCount(); // to check if the book is already issued or not
        // Note: is_available column and field in java class can be skipped. we can always figure out whether a book is available or not by checking the student_id column
//        if(book != null && book.getAvailable()){
//
//        }

        if(book == null || book.getStudent() != null){
            throw new ValidationException("book is not available to issue");
        }

        Student student = this.studentService.findById(studentId);
        if(student == null){
            throw new ValidationException("student id is invalid");
        }

        List<Book> bookList = this.bookService.findBooksIssued(studentId);

        if(bookList == null || bookList.size() >= maxAllowedBooks){
            throw new ValidationException("student has issued more books than max allowed");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ISSUE);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setExternalTxnId(UUID.randomUUID().toString());
        transaction.setBook(book);
        transaction.setStudent(student);

        transaction = this.transactionRepository.save(transaction); // getting the transaction object because it has the id coming from underlying db

        try {
            book.setIsAvailable(false);
            book.setStudent(student);
            book.setIssueCount(originalIssueCount + 1);
            this.bookService.create(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        }catch (Exception e){
            book.setStudent(null);
            book.setIsAvailable(true);
            book.setIssueCount(originalIssueCount);
            bookService.create(book);
            transaction.setTransactionStatus(TransactionStatus.FAILED);

        }finally {
            this.transactionRepository.save(transaction); // if this fails --- edge case's edge case
        }

        return transaction.getExternalTxnId();
    }

    public String returnTxn(Integer studentId, Integer bookId) {

        /**
         * 1. book id should be valid and is issued to the student
         * 2. student id should be valid
         *
         */

        Book book = bookService.findById(bookId);
        if(book == null || book.getStudent() == null || book.getStudent().getId() != studentId){
            throw new ValidationException("book is not issued to the student");
        }

        Student student = this.studentService.findById(studentId);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setExternalTxnId(UUID.randomUUID().toString());
        transaction.setBook(book);
        transaction.setStudent(student);
        transaction.setFine(calculateFine(book, student));

        transactionRepository.save(transaction); // getting the transaction object because it has the id coming from underlying db

        try {
            book.setIsAvailable(true);
            book.setStudent(null);
            bookService.create(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }catch(Exception e){
            book.setStudent(student);
            book.setIsAvailable(false);
            bookService.create(book);
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }finally {
            transactionRepository.save(transaction);
        }

        return null;

    }

    public Long calculateFine(Book book, Student student){
        // (return date - issue date) * fine per day < 0 ? 0 : (return date - issue date) * fine per day
        // Assuming fine is calculated based on the number of days the book is overdue
        // For simplicity, let's say fine is 10 units per day overdue

        Transaction transaction = transactionRepository.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(student, book, TransactionType.ISSUE);

        long daysPassed = ChronoUnit.DAYS.between(transaction.getCreatedAt().toInstant(), new Date().toInstant());

        if((daysPassed - issuanceDaysThreshold) <= 0){
            return 0L; // No fine if the book is returned on or before the due date
        }

        return (daysPassed - issuanceDaysThreshold) * finePerDay;


//        Date issueDate = transaction.getCreatedAt();
//        long issueDateEpoch = issueDate.getTime();
//
//        long dueDateEpoch = issueDateEpoch + issuanceDaysThreshold * 1296000000; // 1296000000 milliseconds in a day
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(issueDate);
//        calendar.add(Calendar.DAY_OF_MONTH, issuanceDaysThreshold)
//
//        long currentDateEpoch = System.currentTimeMillis();
//        long timeDiffEpoch = currentDateEpoch - dueDateEpoch;
//
//        if(timeDiffEpoch < 0){
//            return 0L; // No fine if the book is returned on or before the due date
//        }
//
//        Long daysPassedDueDate = TimeUnit.DAYS.convert(timeDiffEpoch, TimeUnit.MILLISECONDS);
//        return daysPassedDueDate * finePerDay;

    }
}