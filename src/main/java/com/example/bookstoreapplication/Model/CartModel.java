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
    private UserModel user;
    @OneToOne
    private BookModel book;
    private Integer quantity;
    private Integer totalPrice;

    public CartModel(CartDto cartDto, BookModel bookModel, UserModel userModel) {
        this.quantity=cartDto.getQuantity();
        this.totalPrice=cartDto.getQuantity()*bookModel.getPrice();
        this.book=bookModel;
        this.user=userModel;
    }

    public CartModel() {
        super();
    }

}

