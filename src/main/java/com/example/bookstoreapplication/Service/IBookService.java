package com.example.bookstoreapplication.Service;


import com.example.bookstoreapplication.Dto.BookDto;
import com.example.bookstoreapplication.Model.BookModel;

import java.util.List;

public interface IBookService {


    BookModel createBook(BookDto bookDto);

    List<BookModel> getAllBookData();

    BookModel updateRecordById(BookDto bookDto, long id);

    //    @Override
    //    public BookModel deleteRecordById(long id) {
    //        Optional<BookModel> book = bookRepo.findById(id);
    //        bookRepo.delete(book.get());
    //        return book.get();
    //    }
    BookModel deleteBookRecord(long id);

    BookModel getBookModelById(long Id);

    List<BookModel> sortedListOfBooksInAscendingOrder();

    List<BookModel> sortedListOfBooksInDescendingOrder();

    //void deleteRecordByBookId(int bookId);
}
