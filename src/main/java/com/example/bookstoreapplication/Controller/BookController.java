package com.example.bookstoreapplication.Controller;


import com.example.bookstoreapplication.Dto.BookDto;
import com.example.bookstoreapplication.Dto.ResponseDto;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookstore/book")
public class BookController {
    @Autowired
    IBookService bookService;

    @PostMapping("/create")
    public BookModel create(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto);
    }

    @GetMapping("/getlist")
    public List<BookModel> getList(){
        return bookService.getList();
    }

    @PutMapping("/update/{id}")
    public BookModel update(@RequestBody BookDto bookDto,@PathVariable long id) {
        return bookService.update(bookDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public BookModel delete(@PathVariable long id) {
        return bookService.delete(id);
    }


    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> getBookModelById(@PathVariable long Id) {
        BookModel bookModel = bookService.getBookModelById(Id);
        ResponseDto responseDto = new ResponseDto("Success Call for Book Id!!!", bookModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

//    @GetMapping("/sortPriceHighToLow")
//    public ResponseEntity<ResponseDto> sortPriceHighToLow(){
//        BookModel bookModel = (BookModel) bookService.getsortPriceHighToLow();
//        ResponseDto responseDto= new ResponseDto("Geting Book By Id",bookService.sortPriceHighToLow());
//        return new ResponseEntity<>(responseDto,HttpStatus.OK);
//    }
//
//    @GetMapping("/sortPriceLowToHigh")
//    public ResponseEntity<ResponseDto> sortPriceLowToHigh(){
//         BookModel bookModel = bookService.getsortPriceLowToHigh();
//        ResponseDto responseDto= new ResponseDto("Geting Book By Id",bookService.sortPriceLowToHigh());
//        return new ResponseEntity<>(responseDto,HttpStatus.OK);
//    }



}
