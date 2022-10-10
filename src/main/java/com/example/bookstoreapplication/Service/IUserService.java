package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.UserDto;
import com.example.bookstoreapplication.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {


    UserModel createUserModel(UserDto userDto);

    List<UserModel> getList();

    UserModel getUserModelById(long Id);

    UserModel update(UserDto userDto, long id);

    UserModel delete(long id);

    String login(String email, String password);

    Optional<UserModel> verify(String token);
}
