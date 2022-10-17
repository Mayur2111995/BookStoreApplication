package com.example.bookstoreapplication.Service;


import com.example.bookstoreapplication.Dto.CartDto;
import com.example.bookstoreapplication.Model.CartModel;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    CartModel create(String token, CartDto cartDto);

    CartModel delete(long id,String token);

//    CartModel updateQuantity(String token, long cartId, int quantity);

    CartModel updateCart(Long cartId, CartDto cartDto,String token);

     List<CartModel> getCartItemByUserId(String token);

    CartModel updateQuantity(long cartId, int quantity);


    List<CartModel> getAllCartRecords();

    Optional<CartModel> getCartDetailsById(long id);
}
