package com.example.bookstoreapplication.Dto;

import lombok.Data;

@Data
public class OrderDto {

    private Integer quantity;
    private String address;
    private Integer price;
    private Long userID;
    private String email;
    private Long bookID;

    public Object isCancel() {
        return null;
    }
}
