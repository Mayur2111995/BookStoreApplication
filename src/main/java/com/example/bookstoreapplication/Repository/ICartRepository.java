package com.example.bookstoreapplication.Repository;

import com.example.bookstoreapplication.Model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ICartRepository extends JpaRepository <CartModel,Long>{

   @Query(value="select * from cart_model where user_id =:id",nativeQuery=true)
    List<CartModel> findAllByUserId(Long id);
}
