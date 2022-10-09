package com.example.bookstoreapplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Valid
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @Email(message = "Enter a valid Email")
    private String email;
    @NotEmpty(message = "Password Field can't be Empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%!]).{8,}$", message = "Invalid Password")
    private String password;
}
