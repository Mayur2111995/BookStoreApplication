package com.example.bookstoreapplication.Controller;
import com.example.bookstoreapplication.Dto.ResponseDto;
import com.example.bookstoreapplication.Dto.UserDto;
import com.example.bookstoreapplication.Model.UserModel;
import com.example.bookstoreapplication.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> addUserModel(@Valid @RequestBody UserDto userDto) {
        UserModel userModel = userService.createUserModel(userDto);
        ResponseDto responseDto = new ResponseDto("Data ADDED Successfully!!!", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getlist")
    public List<UserModel> getList(){
        return userService.getList();
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> getUserModelById(@PathVariable long Id) {
        UserModel userModel = userService.getUserModelById(Id);
        ResponseDto responseDto = new ResponseDto("Success Call for User Id!!!", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public UserModel update(@RequestBody UserDto userDto , @PathVariable long id) {
        return userService.update(userDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public UserModel delete(@PathVariable long id) {
        return userService.delete(id);

    }

    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password) {
        return userService.login(email,password);
    }
    @PutMapping("/verify/{token}")
    public String verify(@PathVariable String token) {
        return userService.verify(token);
    }

}
