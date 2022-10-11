package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Model.BookModel;
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

    @Override
    public OrderModel createOrder(OrderDto orderDto) {
        Optional<BookModel> book = bookRepo.findById(orderDto.getBookID());  //reference by inject bookrepo

        Optional<UserModel> user = userRepo.findById(orderDto.getUserID());
        if (book.isPresent() && user.isPresent()) {
            if (orderDto.getQuantity() < book.get().getQuantity()) {
                OrderModel newOrder = new OrderModel(orderDto, book.get(), user.get());
                orderRepo.save(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderDto.getQuantity());
                bookRepo.save(book.get());
                return newOrder;
            }
        }
        return null;
    }


    @Override
    public List<OrderModel> getAllOrderRecords() {
        List<OrderModel> orderList = orderRepo.findAll();
        return orderList;
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

//    @Override
//    public OrderModel updateOrderRecord(long id, OrderDto orderDto) {
//        Optional<OrderModel> order = orderRepo.findById(id);
//        Optional<BookModel> book = bookRepo.findById(orderDto.getBookID());
//        Optional<UserModel> user = userRepo.findById(orderDto.getUserID());
//        if (order.isPresent()) {
//            if (book.isPresent() && user.isPresent()) {
//                if (orderDto.getQuantity() < book.get().getQuantity()) {
//                    OrderModel newOrder = new OrderModel(id, orderDto.getQuantity(), orderDto.getAddress(), book.get(), user.get(), orderDto.isCancel());
//                    orderRepo.save(newOrder);
//                    return newOrder;
//                } else {
//                    return null;//Requested quantity is not available
//                }
//            } else {
//                return null;//Book or User doesn't exists
//
//            }
//
//        } else {
//            return null;//Order Record doesn't exist
//        }
//
//    }
}

