package com.example.bookstoreapplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String name;
    private String author;
    private String desc;
    private String logo;
    private Integer price;
    private Integer quantity;



}
