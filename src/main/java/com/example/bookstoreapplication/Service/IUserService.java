package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.BookDto;
import com.example.bookstoreapplication.Dto.LoginDto;
import com.example.bookstoreapplication.Dto.UserDto;
import com.example.bookstoreapplication.Model.UserModel;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface IUserService {


    UserModel createUserModel(UserDto userDto);


    List<UserModel> getAllUserData();

    UserModel getUserModelById(long Id);


    UserModel updateRecordById(UserDto userDto, long id,String token);

    UserModel deleteUserData(long id,String token);

    String login(String email, String password);

    Optional<UserModel> verifyUser(String token);

}
