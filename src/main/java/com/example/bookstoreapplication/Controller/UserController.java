package com.example.bookstoreapplication.Controller;
import com.example.bookstoreapplication.Dto.LoginDto;
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
import java.util.Optional;

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

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAllUserData() {
        List<UserModel> listOfUser = userService.getAllUserData();
        ResponseDto dto = new ResponseDto("Data retrieved successfully :", listOfUser);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> getUserModelById(@PathVariable long Id) {
        UserModel userModel = userService.getUserModelById(Id);
        ResponseDto responseDto = new ResponseDto("Success Call for User Id!!!", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updateUserById/{id}/{token}")
    public ResponseEntity<ResponseDto> updateRecordById(@PathVariable long id, @Valid @RequestBody UserDto userDto,@PathVariable String token) {
        UserModel updateRecord = userService.updateRecordById(userDto, id,token);
        ResponseDto responseDto = new ResponseDto(" User Record updated successfully by Id", updateRecord);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}/{token}")
    public ResponseEntity<ResponseDto> deleteUserData(@PathVariable long id,@PathVariable String token) {
        UserModel userModel= userService.deleteUserData(id,token);
        ResponseDto responseDto=new ResponseDto("User Record Deleted Successfully",userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<ResponseDto> loginUser(@PathVariable String email, @PathVariable String password) {
        String userModel = userService.login(email,password);
        ResponseDto responseDto = new ResponseDto("User Login Successfully",userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/verify/{token}")
    public ResponseEntity<ResponseDto> loginVerify(@PathVariable("token")String token) {
        Optional<UserModel> result = userService.verifyUser(token);
        ResponseDto responseDTO = new ResponseDto("User Successfully Verified", "Verification Status : "
                + result);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    }


