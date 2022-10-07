package com.example.bookstoreapplication.Repository;

import com.example.bookstoreapplication.Model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookModel,Long> {

//    List<BookModel> sortPriceHighToLow();
//
//    List<BookModel> sortPriceLowToHigh();
}
