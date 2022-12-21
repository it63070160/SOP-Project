package com.example.orderservice.query.rest;

import com.example.orderservice.core.UserOrderedEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRestModel {
    private String orderId;
    private String buyer;
    private String buyerAddress;
    private List<UserOrderedEntity> bookList;
    private BigDecimal total;
}
