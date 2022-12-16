package com.example.productservice.query;

import com.example.productservice.core.BookEntity;
import com.example.productservice.core.data.BookRepository;
import com.example.productservice.core.event.BookCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {
    private final BookRepository bookRepository;

    public BookEventsHandler(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @EventHandler
    public void on(BookCreatedEvent event){
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(event, bookEntity);
        bookRepository.save(bookEntity);
    }
}
