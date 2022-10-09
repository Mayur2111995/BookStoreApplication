package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.BookDto;
import com.example.bookstoreapplication.Exception.BookException;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Repository.IBookRepository;
import com.example.bookstoreapplication.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService  implements IBookService {

    @Autowired
    IBookRepository bookRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public BookModel create(BookDto bookDto) {
        BookModel book = new BookModel(bookDto);
        bookRepo.save(book);
        return book;
    }

    @Override
    public List<BookModel> getList() {
        List<BookModel> lst = bookRepo.findAll();
        return lst;
    }

    @Override
    public BookModel update(BookDto bookDto, long id) {
        Optional<BookModel> book = bookRepo.findById(id);
        book.get().setAuthor(bookDto.getAuthor());
        book.get().setDesc(bookDto.getDesc());
        book.get().setLogo(bookDto.getLogo());
        book.get().setName(bookDto.getName());
        book.get().setPrice(bookDto.getPrice());
        book.get().setQuantity(bookDto.getQuantity());
        bookRepo.save(book.get());
        return book.get();
    }

    @Override
    public BookModel delete(long id) {
        Optional<BookModel> book = bookRepo.findById(id);
        bookRepo.delete(book.get());
        return book.get();
    }
    @Override
    public BookModel getBookModelById(long Id) {
        return bookRepo.findById(Id)
                .orElseThrow(() -> new BookException("Book not found In the List"));
    }

//    @Override
//    public List<BookModel> getsortPriceHighToLow(){
//        List<BookModel> bookModel=bookRepo.sortPriceHighToLow();
//        return bookModel;
//    }
//    @Override
//    public List<BookModel> getsortPriceLowToHigh(){
//        List<BookModel> bookModel=bookRepo.sortPriceLowToHigh();
//        return bookModel;
//    }




}
