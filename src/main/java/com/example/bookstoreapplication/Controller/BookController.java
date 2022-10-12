package com.example.bookstoreapplication.Controller;
import com.example.bookstoreapplication.Dto.BookDto;
import com.example.bookstoreapplication.Dto.ResponseDto;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Model.OrderModel;
import com.example.bookstoreapplication.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore/book")
public class BookController {
    @Autowired
    IBookService bookService;


    @PostMapping("/save")
    public ResponseEntity<ResponseDto> addUserInBookStore(@Valid @RequestBody BookDto bookDto) {
        BookModel newBook = bookService.createBook(bookDto);
        ResponseDto responseDto = new ResponseDto("Created the new book in book store", newBook);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAllBookData() {
        List<BookModel> listOfBooks = bookService.getAllBookData();
        ResponseDto dto = new ResponseDto("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @PutMapping("/updateBookById/{bookId}")
    public ResponseEntity<ResponseDto> updateRecordById(@PathVariable long bookId, @Valid @RequestBody BookDto bookDto) {
        BookModel updateRecord = bookService.updateRecordById(bookDto, bookId);
        ResponseDto responseDto = new ResponseDto(" Book Record updated successfully by Id", updateRecord);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDto> deleteRecordById(@PathVariable long id){
        BookModel bookModel = bookService.deleteBookRecord(id);
        ResponseDto responseDto = new ResponseDto("Record deleted successfully !",bookModel);
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }


    //public String deleteRecordById(@PathVariable int bookId) {
      //  bookService.deleteRecordByBookId(bookId);
        //return "Book Deleted.";


    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> getBookModelById(@PathVariable long Id) {
        BookModel bookModel = bookService.getBookModelById(Id);
        ResponseDto responseDto = new ResponseDto("Success Call for Book Id!!!", bookModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDto> getBooksInAscendingOrder() {
        List<BookModel> listOfBooks = bookService.sortedListOfBooksInAscendingOrder();
        ResponseDto responseDto = new ResponseDto("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDto> getBooksInDescendingOrder() {
        List<BookModel> listOfBooks = bookService.sortedListOfBooksInDescendingOrder();
        ResponseDto responseDto = new ResponseDto("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(responseDto, HttpStatus.OK);

    }

}
