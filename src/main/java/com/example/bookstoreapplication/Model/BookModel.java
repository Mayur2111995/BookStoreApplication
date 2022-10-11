package com.example.bookstoreapplication.Model;

import com.example.bookstoreapplication.Dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long bookId;
    @Column(name="bookname")
    private String name;
    @Column(name="bookauthor")
    private String author;
    @Column(name="bookdesc")
    private String desc;
    @Column(name="bookogo")
    private String logo;
    @Column(name="bookprice")
    private Integer price;
    @Column(name="bookquantity")
    private int quantity;

    public BookModel(BookDto bookDto) {

        this.name=bookDto.getName();
        this.author=bookDto.getAuthor();
        this.desc=bookDto.getDesc();
        this.logo=bookDto.getLogo();
        this.price=bookDto.getPrice();
        this.quantity=bookDto.getQuantity();

    }

    public BookModel() {
        super();
    }

//    public Object getBookName() {
//        return null;
//    }
}
