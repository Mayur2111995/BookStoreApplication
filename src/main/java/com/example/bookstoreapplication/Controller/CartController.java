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

    @PostMapping("/create/{token}")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CartDto cartDto,@PathVariable String token) {
        CartModel cartModel = cartService.create(token, cartDto);
        ResponseDto dto = new ResponseDto("Cart registered successfully !", cartModel);
        return new ResponseEntity(dto, HttpStatus.CREATED);

    }

    @PutMapping("/updatecart/{token}")
    public ResponseEntity<ResponseDto> updateCart(@PathVariable long id, @Valid @RequestBody CartDto cartDto,@PathVariable String token) {
        CartModel updateRecord = cartService.updateCart(id, cartDto,token);
        ResponseDto responseDto = new ResponseDto(" Cart Record updated successfully by Id", updateRecord);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/delete/{id}/{token}")
    public ResponseEntity<ResponseDto> delete(@PathVariable long id,@PathVariable String token) {
        CartModel cartModel = cartService.delete(id,token);
        ResponseDto responseDto = new ResponseDto("Cart Record Deleted Successfully", cartModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
    @GetMapping("/getCartByUserId/{token}")
    public ResponseEntity<ResponseDto> getCartByUserId(@PathVariable String token){
        List<CartModel> carts = cartService.getCartItemByUserId(token);
        ResponseDto responseDTO = new ResponseDto("Cart details for User ", carts);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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