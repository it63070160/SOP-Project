package com.example.bookservice.command.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBookRestModel {
    private String bookName;
    private String bookDescription;
    private String bookType;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
    private String checkOutType;
    private String ownerId;
}
