package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.UserDto;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Model.UserModel;

import java.util.List;

public interface IUserService {

    UserModel createUserModel(UserDto userDto);

    List<UserModel> getList();

    UserModel getUserModelById(long Id);


    UserModel update(UserDto userDto, long id);

    UserModel delete(long id);
}
