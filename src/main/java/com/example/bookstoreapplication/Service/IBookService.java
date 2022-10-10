package com.example.bookstoreapplication.Service;


import com.example.bookstoreapplication.Dto.BookDto;
import com.example.bookstoreapplication.Model.BookModel;

import java.util.List;

public interface IBookService {


    BookModel createBook(BookDto bookDto);

    List<BookModel> getList();

    BookModel update(BookDto bookDto, long id);

    BookModel delete(long id);

    BookModel getBookModelById(long Id);

    List<BookModel> sortedListOfBooksInAscendingOrder();

    List<BookModel> sortedListOfBooksInDescendingOrder();
}
