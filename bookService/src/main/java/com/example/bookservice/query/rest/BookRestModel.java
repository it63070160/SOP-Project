package com.example.bookservice.query.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookRestModel {
    private String bookId;
    private String bookName;
    private String bookDescription;
    private String bookType;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
    private String checkOutType;
    private String ownerId;
}
