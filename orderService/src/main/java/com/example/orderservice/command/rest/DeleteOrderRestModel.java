package com.example.orderservice.command.rest;
import com.example.orderservice.core.UserOrderedEntity;
import lombok.Data;

import java.util.List;

@Data
public class DeleteOrderRestModel {
    private String id;
    private String bookList;
}
