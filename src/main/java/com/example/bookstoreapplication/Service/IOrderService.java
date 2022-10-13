package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Model.OrderModel;

import java.util.List;

public interface IOrderService {


    OrderModel createOrder(OrderDto orderDto,String token);

    List<OrderModel> getAllOrderRecords();

    OrderModel cancelOrderRecord(long id,String token);

    List<OrderModel> getOrderItemByUserId(String token);
}
