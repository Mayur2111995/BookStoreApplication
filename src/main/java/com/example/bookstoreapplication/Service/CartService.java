package com.example.bookstoreapplication.Service;


import com.example.bookstoreapplication.Dto.CartDto;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Model.CartModel;
import com.example.bookstoreapplication.Model.UserModel;
import com.example.bookstoreapplication.Repository.IBookRepository;
import com.example.bookstoreapplication.Repository.ICartRepository;
import com.example.bookstoreapplication.Repository.IUserRepository;
import com.example.bookstoreapplication.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepository cartRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IUserRepository userRepo;
    @Autowired
    IBookRepository bookRepo;

    @Override
    public CartModel create(String token, CartDto cartDto) {
        long userID = Math.toIntExact(tokenUtil.decodeToken(token));
        Optional<BookModel> book = bookRepo.findById(cartDto.getBookID());
        Optional<UserModel> user = userRepo.findById(userID);
        if (book.isPresent() && user.isPresent()) {
            if (cartDto.getQuantity() < book.get().getQuantity()) {
                List<CartModel> existingCustomer=cartRepo.getCartsByUserId(userID);
                if(!existingCustomer.isEmpty()){
                    System.out.println("existing customer");
                    CartModel newCart = new CartModel(existingCustomer.get(0).getId(),cartDto,book.get(),user.get());
                    newCart.setQuantity(existingCustomer.get(0).getQuantity()+newCart.getQuantity());
                    newCart.setTotalPrice(existingCustomer.get(0).getTotalPrice()+newCart.getTotalPrice());
                    cartRepo.save(newCart);
                    book.get().setQuantity(book.get().getQuantity() - cartDto.getQuantity());
                    bookRepo.save(book.get());
                    return newCart;
                }else{
                CartModel newCart = new CartModel(cartDto,book.get(),user.get());
                cartRepo.save(newCart);
                book.get().setQuantity(book.get().getQuantity() - cartDto.getQuantity());
                bookRepo.save(book.get());
                return newCart;}
            }
        }
        return null;
    }


    @Override
    public CartModel delete(long id,String token) {
        Optional<CartModel> cart = cartRepo.findById(id);
        cartRepo.delete(cart.get());
        return cart.get();
    }

    @Override
    public CartModel updateCart(Long id, CartDto cartDto,String token) {
        Long userid= tokenUtil.decodeToken(token);
        Optional<CartModel> cart = cartRepo.findById(id);
        Optional<BookModel> book = bookRepo.findById(cartDto.getBookID());
        Optional<UserModel> user = userRepo.findById(userid);
        if (book.isPresent() && user.isPresent()) {
            if (cartDto.getQuantity() < book.get().getQuantity()) {
                cart.get().setQuantity(cartDto.getQuantity());
                cart.get().setBookID(book.get());
                cartRepo.save(cart.get());
                book.get().setQuantity(book.get().getQuantity() - (cartDto.getQuantity() - cart.get().getQuantity()));
                bookRepo.save(book.get());
                return cart.get();
            }
        }
        return null;
    }


//    @Override
//    public List<CartModel> gerCartForUser(long id) {
////		Long id = tokenUtil.decodeToken(token);
//        Optional<UserModel> user = userRepo.findById(id);
//        List<CartModel> cartList = cartRepo.findAllByUserId(id);
//        return cartList;
//    }
//     @Override
//     public List<CartModel> getCartItemByUserId(String token) {
//     int id = Math.toIntExact(tokenUtil.decodeToken(token));
//     List<CartModel> cartList= cartRepo.getCartsByUserId(id);
//     if (cartList.isEmpty())
//        throw new BookException("Cart with User token "+token+" not found!");
//    return cartList;
//}
      @Override
     public List<CartModel> getCartItemByUserId(String token) {
     long id = tokenUtil.decodeToken(token);
     List<CartModel> cartList = cartRepo.getCartsByUserId(id);
     return cartList;
}

//    @Override
//    public CartModel updateQuantity(String token, long cartId, int quantity) {
//        Long id = tokenUtil.decodeToken(token);
//        Optional<UserModel> user = userRepo.findById(id);
//        if (user.isPresent()) {
//            Optional<CartModel> cart = cartRepo.findById(cartId);
//            cart.get().setQuantity(quantity);
//            cart.get().setTotalPrice(quantity*cart.get().getBook().getPrice());
//            cartRepo.save(cart.get());
//            return cart.get();
//        }
//        return null;
//    }

    @Override
    public CartModel updateQuantity(long cartId, int quantity) {
        Optional<CartModel> cart = cartRepo.findById(cartId);
        cart.get().setQuantity(quantity);
        cart.get().setTotalPrice(quantity*cart.get().getBookID().getPrice());
        cartRepo.save(cart.get());
        return cart.get();
    }
    @Override
    public List<CartModel> getAllCartRecords() {
        List<CartModel> cartList = cartRepo.findAll();
        return cartList;
    }
    @Override
    public Optional<CartModel> getCartDetailsById(long id){
        Optional<CartModel> cart=cartRepo.findById(id);
        return cart;
    }


}
