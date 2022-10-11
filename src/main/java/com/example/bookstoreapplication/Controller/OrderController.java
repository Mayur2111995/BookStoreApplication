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

@PostMapping("/bookOrder")
public ResponseEntity<ResponseDto> createOrder(@Valid @RequestBody OrderDto orderDto){
    OrderModel newOrder = orderService.createOrder(orderDto);
    ResponseDto dto = new ResponseDto("Order registered successfully !",newOrder);
    return new ResponseEntity(dto, HttpStatus.CREATED);
}

    @GetMapping("/AllOrders")
    public ResponseEntity<ResponseDto> getAllOrderRecords(){
    List<OrderModel> newOrder = orderService.getAllOrderRecords();
    ResponseDto responseDto = new ResponseDto("All records retrieved successfully !",newOrder);
    return new ResponseEntity(responseDto, HttpStatus.OK);
}

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<ResponseDto> deleteOrderRecord(@PathVariable long id){
        OrderModel newOrder = orderService.deleteOrderRecord(id);
        ResponseDto responseDto = new ResponseDto("Record deleted successfully !",newOrder);
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }

}
