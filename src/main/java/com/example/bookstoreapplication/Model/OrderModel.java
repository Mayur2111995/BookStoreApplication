package com.example.bookstoreapplication.Model;


import com.example.bookstoreapplication.Dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @OneToOne
    @JoinColumn(name="userID")
    private UserModel user;
    @OneToOne
    @JoinColumn(name="bookID")
    private BookModel book;
    private boolean cancel;


    public OrderModel(OrderDto orderDto, BookModel bookModel, UserModel userModel) {
        this.address=orderDto.getAddress();
        this.price=orderDto.getPrice();
        this.quantity=orderDto.getQuantity();
        this.user=userModel;
        this.book=bookModel;
    }

    public OrderModel() {

    }
}
