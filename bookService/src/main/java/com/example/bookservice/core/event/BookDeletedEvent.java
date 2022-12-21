package com.example.bookservice.core.event;

import lombok.Data;

@Data
public class BookDeletedEvent {
    private String bookId;
}
