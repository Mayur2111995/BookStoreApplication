package com.example.bookstoreapplication.Dto;

import lombok.Data;

@Data
public class CartDto {
    private Long userID;
    private Long bookID;
    private Integer quantity;

}
