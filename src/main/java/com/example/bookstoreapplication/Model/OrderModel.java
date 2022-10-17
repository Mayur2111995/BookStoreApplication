package com.example.bookstoreapplication.Model;


import com.example.bookstoreapplication.Dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor

public class OrderModel {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    @CreationTimestamp
    private LocalDate OrderDate;
    private double price;
    private int quantity;
    private String address;
    private Integer totalPrice;


    @OneToOne
    @JoinColumn(name="userID")
    private UserModel user;
    @OneToOne
    @JoinColumn(name="bookID")
    private BookModel book;
    private boolean cancel;
    @JoinColumn(name="id")
    @OneToOne
    private CartModel cartModel;


    public OrderModel(OrderDto orderDto, BookModel bookModel, UserModel userModel,CartModel cartModel) {
        this.address=orderDto.getAddress();
        this.price=bookModel.getPrice();
        this.quantity=cartModel.getQuantity();
        this.user=userModel;
        this.book=bookModel;
        this.cartModel=cartModel;
        this.totalPrice=cartModel.getTotalPrice();
    }

    public OrderModel() {


    }

    public OrderModel(long id, Integer quantity, String address, BookModel bookModel, UserModel userModel, Object cancel, Object bookName) {

    }

    public OrderModel(long id, Integer quantity, String address, BookModel bookModel, UserModel userModel, Object cancel) {
    }

}
