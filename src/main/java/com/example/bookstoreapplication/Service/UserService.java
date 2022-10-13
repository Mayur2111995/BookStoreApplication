package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.UserDto;
import com.example.bookstoreapplication.Exception.BookException;
import com.example.bookstoreapplication.Model.UserModel;
import com.example.bookstoreapplication.Repository.IUserRepository;
import com.example.bookstoreapplication.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepo;

    @Autowired
    TokenUtil tokenUtil;



    @Override
    public UserModel createUserModel(UserDto userDto) {
        UserModel userModel = new UserModel(userDto);
        userRepo.save(userModel);

       String token = tokenUtil.createToken((long) userModel.getId());

        String subject="User Registration Succesfull";
       String body = "http://localhost:8083/bookstore/verify/"+token;
        return userModel;
    }


    @Override
    public List<UserModel> getAllUserData() {
        List<UserModel> getUser=userRepo.findAll();
        return getUser;
    }
    @Override
    public UserModel getUserModelById(long Id) {
        return userRepo.findById(Id)
                .orElseThrow(() -> new BookException("User not found In the List"));
    }
    @Override
    public UserModel updateRecordById(UserDto userDto, long id,String token) {
        Optional<UserModel> user = userRepo.findById(id);
        user.get().setFirstName(userDto.getFirstName());
        user.get().setLastName(userDto.getLastName());
        user.get().setDob(userDto.getDob());
        user.get().setPassword(userDto.getPassword());
        user.get().setEmail(userDto.getEmail());
        userRepo.save(user.get());
        return user.get();
    }

    @Override
    public UserModel deleteUserData(long id,String token) {
        Optional<UserModel> user = userRepo.findById(id);
        userRepo.delete(user.get());
        return user.get();
    }

    @Override
    public String login(String email, String password) {

        Optional<UserModel> user = userRepo.findByEmail(email);
        String token = "token";

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                token = tokenUtil.createToken((long) user.get().getId());
                user.get().setToken(token);
                userRepo.save(user.get());
            }

        }
        return token;
    }

    @Override
    public Optional<UserModel> verifyUser(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<UserModel> user = userRepo.findById(id);
        if(user.isPresent()) {
            user.get().setVerify(true);
            userRepo.save(user.get());
        }
        return user;
    }

        }
