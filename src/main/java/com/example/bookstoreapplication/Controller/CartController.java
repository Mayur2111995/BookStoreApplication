package com.example.bookstoreapplication.Controller;

import com.example.bookstoreapplication.Dto.CartDto;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Model.CartModel;
import com.example.bookstoreapplication.Service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/create")
    public CartModel create(@RequestBody CartDto cartDto) {
        return cartService.create(cartDto);
    }

    @PutMapping("/updatecart")
    public CartModel updateCart(@PathVariable Long cartId,@RequestBody CartDto cartDto) {
        return cartService.updateCart(cartId,cartDto);
    }

    @DeleteMapping("/delete/{id}")
    public CartModel delete(@PathVariable long id){
        return cartService.delete(id);
    }

    @GetMapping("/getcartforuser/{id}")
    public List<CartModel> getCartforUser(@PathVariable long id){
        return cartService.gerCartForUser(id);
    }

    @PutMapping("/updatequantity/{cartId}/{quantity}")
    public CartModel updateQuantity(@PathVariable long cartId,@PathVariable int quantity) {
        return cartService.updateQuantity(cartId,quantity);
    }

    @GetMapping("/getcartcount")
    public int getCount() {
        return cartService.getCount();
    }
    @GetMapping("/getlist")
    public List<CartModel> getList(){
        return cartService.getList();
    }
    }
