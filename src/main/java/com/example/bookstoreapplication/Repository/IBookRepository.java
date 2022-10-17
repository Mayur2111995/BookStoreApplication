package com.example.bookstoreapplication.Repository;

import com.example.bookstoreapplication.Model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<BookModel,Long> {
    @Query(value = "select * from book_model ", nativeQuery = true)  //order by price
    List<BookModel> getSortedListOfBooksInAsc();

    @Query(value = "select * from book_model ", nativeQuery = true)   //order by price desc
    List<BookModel> getSortedListOfBooksInDesc();

}
