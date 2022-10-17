package com.example.bookstoreapplication.Model;


import com.example.bookstoreapplication.Dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private UserModel userID;
    @OneToOne
    private BookModel bookID;
    private Integer quantity;
    private Integer totalPrice;

    public CartModel(CartDto cartDto, BookModel bookModel, UserModel userModel) {
        this.quantity=cartDto.getQuantity();
        this.totalPrice=cartDto.getQuantity()*bookModel.getPrice();
        this.bookID=bookModel;
        this.userID=userModel;
    }
    public CartModel(long Id,CartDto cartDto, BookModel bookModel, UserModel userModel) {
        this.id=Id;
        this.quantity=cartDto.getQuantity();
        this.totalPrice=cartDto.getQuantity()*bookModel.getPrice();
        this.bookID=bookModel;
        this.userID=userModel;
    }

    public CartModel() {
        super();
    }
}

