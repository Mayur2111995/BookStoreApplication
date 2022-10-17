package com.example.bookstoreapplication.Dto;
import lombok.Data;

@Data
public class OrderDto {

    private Integer quantity;
    private String address;
    private Integer price;
    private String email;
    private Long bookID;
    private Integer totalPrice;
    private Long cartID;

}
