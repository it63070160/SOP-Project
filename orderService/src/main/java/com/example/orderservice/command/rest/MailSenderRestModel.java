package com.example.orderservice.command.rest;

import lombok.Data;

@Data
public class MailSenderRestModel {
    private String bookId;
    private String bookName;
    private Integer orderQuantity;
}