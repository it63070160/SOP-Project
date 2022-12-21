package com.example.bookservice.query;

import com.example.bookservice.core.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import com.example.bookservice.core.event.BookCreatedEvent;
import com.example.bookservice.core.event.BookDeletedEvent;
import com.example.bookservice.core.event.BookEditedEvent;
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

    @EventHandler
    public void on(BookEditedEvent event){
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(event, bookEntity);
        bookRepository.save(bookEntity);
    }

    @EventHandler
    public void on(BookDeletedEvent event){
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(event, bookEntity);
        bookRepository.delete(bookEntity);
    }
}
