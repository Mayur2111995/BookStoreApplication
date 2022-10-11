package com.example.bookstoreapplication.Dto;

import lombok.Data;


@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String password;
    private String email;
    private String token;

//    public int getErrorCode() {
//        return 0;
//    }
}
