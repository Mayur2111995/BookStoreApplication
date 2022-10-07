package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Model.OrderModel;

import java.util.List;

public interface IOrderService {


    OrderModel create(OrderDto orderDto);

    List<OrderModel> getOrdersForUser(long id);
}
