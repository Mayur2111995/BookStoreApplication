package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Email.EmailService;
import com.example.bookstoreapplication.Model.BookModel;
import com.example.bookstoreapplication.Model.CartModel;
import com.example.bookstoreapplication.Model.OrderModel;
import com.example.bookstoreapplication.Model.UserModel;
import com.example.bookstoreapplication.Repository.IBookRepository;
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
    IBookRepository bookRepo;
    @Autowired
    IOrderRepository orderRepo;

    @Autowired
    MailService mailService;

    @Override
    public OrderModel createOrder(OrderDto orderDto) {
        Optional<BookModel> book = bookRepo.findById(orderDto.getBookID());
        Optional<UserModel> user = userRepo.findById(orderDto.getUserID());
        if(book.isPresent() && user.isPresent()) {
            if(orderDto.getQuantity() < book.get().getQuantity()) {
                OrderModel newOrder = new OrderModel(orderDto,book.get(),user.get());
                orderRepo.save(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderDto.getQuantity());
                bookRepo.save(book.get());
                mailService.send(user.get().getEmail(),"Your Order Placed successfully", String.valueOf(newOrder));
                return newOrder;
            }
        }
        return null;
    }

    @Override
    public List<OrderModel> getAllOrderRecords() {
        List<OrderModel> getOrder=orderRepo.findAll();
        return getOrder;
    }

    @Override
    public OrderModel deleteOrderRecord(long id) {
        Optional<OrderModel> order = orderRepo.findById(id);
        if (order.isPresent()) {
            orderRepo.deleteById(id);
            return order.get();

        } else {
            return null;     //Order Record doesn't exists
        }
    }


}

