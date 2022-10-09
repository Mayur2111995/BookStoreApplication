package com.example.bookstoreapplication.Controller;


import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Dto.ResponseDto;
import com.example.bookstoreapplication.Model.OrderModel;
import com.example.bookstoreapplication.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("/create")
    public OrderModel create(@RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);

    }

    @GetMapping("/getordersforuser/{id}")
    public List<OrderModel> getOrdersForUser(@PathVariable long id){
        return orderService.getOrdersForUser(id);
    }

//    @DeleteMapping("/cancelorder/{orderId}")
//    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable long Id){
//        OrderModel orderModel = orderService.delete(Id);
//        ResponseDto responseDto = new ResponseDto(200, "Successfull", orderModel);
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }

}





