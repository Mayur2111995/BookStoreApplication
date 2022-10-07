package com.example.bookstoreapplication.Dto;

import com.example.bookstoreapplication.Model.OrderModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object data;

    public ResponseDto (String  message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDto(int i, String successfull, OrderModel order) {

    }
}
