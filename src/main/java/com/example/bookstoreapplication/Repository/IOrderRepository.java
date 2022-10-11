package com.example.bookstoreapplication.Repository;

import com.example.bookstoreapplication.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderModel,Long> {

    @Query(value = "SELECT * FROM mybookstore.order;", nativeQuery = true)
    List<OrderModel> listOrder();

}
