package com.example.bookstoreapplication.Service;


import com.example.bookstoreapplication.Dto.CartDto;
import com.example.bookstoreapplication.Model.CartModel;

import java.util.List;

public interface ICartService {


    CartModel create(CartDto cartDto);

    CartModel delete(long id);

    CartModel updateCart(Long id, CartDto cartDto);

    List<CartModel> gerCartForUser(long id);

    CartModel updateQuantity(long cartId, int quantity);

    int getCount();

    List<CartModel> getList();
}
