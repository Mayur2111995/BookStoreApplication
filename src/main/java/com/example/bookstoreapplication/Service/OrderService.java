package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Exception.BookException;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Model.CartModel;
import com.example.bookstoreapplication.Model.OrderModel;
import com.example.bookstoreapplication.Model.UserModel;
import com.example.bookstoreapplication.Repository.IBookRepository;
import com.example.bookstoreapplication.Repository.ICartRepository;
import com.example.bookstoreapplication.Repository.IOrderRepository;
import com.example.bookstoreapplication.Repository.IUserRepository;
import com.example.bookstoreapplication.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IUserRepository userRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    ICartRepository cartRepo;

    @Autowired
    IBookRepository bookRepo;
    @Autowired
    IOrderRepository orderRepo;

    @Autowired
    ICartService cartService;

    @Autowired
    MailService mailService;



    @Override
    public OrderModel createOrder(OrderDto orderDto, String token) {
        Long userId=tokenUtil.decodeToken(token);
        Optional<CartModel> cart = cartService.getCartDetailsById(orderDto.getCartID()); //instance cartservice

        Optional<BookModel> book = bookRepo.findById(cart.get().getBookID().getBookId());
        System.out.println("test"+book.get());
        Optional<UserModel> user = userRepo.findById(userId);

//
        if(book.isPresent() && user.isPresent()) {
           // if(orderDto.getQuantity() < book.get().getQuantity()) {
                OrderModel newOrder = new OrderModel(orderDto,book.get(),user.get(),cart.get());
                orderRepo.save(newOrder);
               // book.get().setQuantity(book.get().getQuantity() - orderDto.getQuantity());
              //  bookRepo.save(book.get());
                mailService.send(user.get().getEmail(),"Your Order Placed successfully",
                        String.valueOf(user.get()),String.valueOf(book.get()),String.valueOf(newOrder.getTotalPrice()));
                return newOrder;
           // }
        }
        return null;
    }

    @Override
    public List<OrderModel> getAllOrderRecords() {
        List<OrderModel> getOrder=orderRepo.findAll();
        return getOrder;
    }

    @Override
    public OrderModel cancelOrderRecord(String token, long Id) {
    long id = tokenUtil.decodeToken(token);
    Optional<UserModel> user = userRepo.findById(id);
    if (user.isPresent()) {
        OrderModel order = orderRepo.getById(Id);
        order.setCancel(true);
        return orderRepo.save(order);
    } else {
        throw new BookException("Invalid order Id");
    }

}

    @Override
    public List<OrderModel> getOrderItemByUserId(String token) {
        long id = tokenUtil.decodeToken(token);
        List<OrderModel> orders = orderRepo.findAllByUserId(id);
        if(orders.isEmpty())
            throw new BookException("No order placed by user with id "+id+"!");
        return orders;
    }
}

