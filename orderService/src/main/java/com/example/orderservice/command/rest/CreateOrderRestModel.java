package com.example.orderservice.command.rest;

import com.example.orderservice.core.UserOrderedEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderRestModel {
    private String buyer;
    private List<UserOrderedEntity> bookList;
    private String bookList_String;
    private String buyerAddress;
    private BigDecimal total;
}
