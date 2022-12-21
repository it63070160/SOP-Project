package com.example.orderservice.core.data;

import com.example.orderservice.core.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    OrderEntity findByOrderId(String orderId);

    List<OrderEntity> findByBuyer(String buyerId);
}
