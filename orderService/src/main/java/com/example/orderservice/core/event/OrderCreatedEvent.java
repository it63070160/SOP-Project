package com.example.orderservice.core.event;

import com.example.orderservice.core.UserOrderedEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String buyer;
    private List<UserOrderedEntity> bookList;
    private BigDecimal total;
}
