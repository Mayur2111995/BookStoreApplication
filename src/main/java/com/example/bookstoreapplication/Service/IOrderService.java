package com.example.bookstoreapplication.Service;

import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Model.OrderModel;

import java.util.List;

public interface IOrderService {


    OrderModel createOrder(OrderDto orderDto);

    List<OrderModel> getAllOrderRecords();

    OrderModel deleteOrderRecord(long id);

}
