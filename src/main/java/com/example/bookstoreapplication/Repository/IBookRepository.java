package com.example.bookstoreapplication.Repository;

import com.example.bookstoreapplication.Model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<BookModel,Long> {
    @Query(value = "select * from book order by price", nativeQuery = true)
    List<BookModel> getSortedListOfBooksInAsc();

    @Query(value = "select * from book order by price desc", nativeQuery = true)
    List<BookModel> getSortedListOfBooksInDesc();

}
