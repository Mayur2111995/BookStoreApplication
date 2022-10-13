package com.example.bookstoreapplication.Controller;
import com.example.bookstoreapplication.Dto.OrderDto;
import com.example.bookstoreapplication.Dto.ResponseDto;
import com.example.bookstoreapplication.Model.OrderModel;
import com.example.bookstoreapplication.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

@PostMapping("/create/{token}")
public ResponseEntity<ResponseDto> createOrder(@PathVariable String token, @Valid @RequestBody OrderDto orderDto){
    OrderModel newOrder = orderService.createOrder(orderDto,token);
    ResponseDto dto = new ResponseDto("Order registered successfully !",newOrder);
    return new ResponseEntity(dto, HttpStatus.CREATED);
}

    @GetMapping("/AllOrders")
    public ResponseEntity<ResponseDto> getAllOrderRecords(){
    List<OrderModel> newOrder = orderService.getAllOrderRecords();
    ResponseDto responseDto = new ResponseDto("All records retrieved successfully !",newOrder);
    return new ResponseEntity(responseDto, HttpStatus.OK);
}

    @PutMapping("/cancelOrder/{id}/{token}")
    public ResponseEntity<ResponseDto> cancelOrderRecord(@PathVariable long id, @PathVariable String token){
        OrderModel newOrder = orderService.cancelOrderRecord(id,token);
        ResponseDto responseDto = new ResponseDto("Record cancel successfully !",newOrder);
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getOrderByUserId/{token}")
    public ResponseEntity<ResponseDto> getOrderById(@PathVariable String token){
        List<OrderModel> order = orderService.getOrderItemByUserId(token);
        ResponseDto responseDto = new ResponseDto("Order details for user successful", order);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
