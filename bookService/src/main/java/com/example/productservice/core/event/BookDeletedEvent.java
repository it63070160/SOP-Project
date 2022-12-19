package com.example.productservice.core.event;

import lombok.Data;

@Data
public class BookDeletedEvent {
    private String bookId;
}
