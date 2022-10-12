package com.example.bookstoreapplication.Controller;

import com.example.bookstoreapplication.Dto.CartDto;
import com.example.bookstoreapplication.Dto.ResponseDto;
import com.example.bookstoreapplication.Model.CartModel;
import com.example.bookstoreapplication.Service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CartDto cartDto) {
        CartModel cartModel = cartService.create(cartDto);
        ResponseDto dto = new ResponseDto("Cart registered successfully !", cartModel);
        return new ResponseEntity(dto, HttpStatus.CREATED);

    }

    @PutMapping("/updatecart")
    public ResponseEntity<ResponseDto> updateCart(@PathVariable long id, @Valid @RequestBody CartDto cartDto) {
        CartModel updateRecord = cartService.updateCart(id, cartDto);
        ResponseDto responseDto = new ResponseDto(" Cart Record updated successfully by Id", updateRecord);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable long id) {
        CartModel cartModel = cartService.delete(id);
        ResponseDto responseDto = new ResponseDto("Cart Record Deleted Successfully", cartModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @GetMapping("/getcartforuser/{id}")
    public ResponseEntity<ResponseDto> getCartforUser(@PathVariable long id) {
        List<CartModel> listOfCart = cartService.gerCartForUser(id);
        ResponseDto responseDto = new ResponseDto("Cart Record  Successfully", listOfCart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);


    }

    @PutMapping("/updatequantity/{cartId}/{quantity}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable long cartId, @PathVariable int quantity) {
        CartModel updateRecord = cartService.updateQuantity(cartId, quantity);
        ResponseDto responseDto = new ResponseDto(" Cart Record updated successfully by Id", updateRecord);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);

    }

    @GetMapping("/AllCart")
    public ResponseEntity<ResponseDto> getAllCartRecords() {
        List<CartModel> newCart = cartService.getAllCartRecords();
        ResponseDto responseDto = new ResponseDto("All records retrieved successfully !", newCart);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

}