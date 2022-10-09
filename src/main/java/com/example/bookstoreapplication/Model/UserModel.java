package com.example.bookstoreapplication.Model;


import com.example.bookstoreapplication.Dto.UserDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String password;
    private String email;

    private boolean verify;


    public UserModel(UserDto userDto) {
     this.firstName=userDto.getFirstName();
     this.lastName=userDto.getLastName();
     this.dob=userDto.getDob();
     this.password=userDto.getPassword();
     this.email=userDto.getEmail();
    }

    public UserModel() {
        super();


    }

    public UserModel(String login_successfully) {

    }

    public void updateUserModel(UserDto userDto) {

    }

    public void update(UserDto userDto) {

    }
}
