package com.example.orderservice.core.event;

import lombok.Data;

@Data
public class OrderDeletedEvent {
    private String orderId;
}
