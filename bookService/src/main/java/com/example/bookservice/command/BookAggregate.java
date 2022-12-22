package com.example.bookservice.command;

import com.example.bookservice.core.event.BookCreatedEvent;
import com.example.bookservice.core.event.BookDeletedEvent;
import com.example.bookservice.core.event.BookEditedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class BookAggregate {

    @AggregateIdentifier
    private String bookId;
    private String bookName;
    private String bookDescription;
    private String bookType;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
    private String checkOutType;
    private String ownerId;

    public BookAggregate(){}

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand){
        if(createBookCommand.getBookName() == null || createBookCommand.getBookName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(createBookCommand.getBookDescription() == null || createBookCommand.getBookDescription().isBlank()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if(createBookCommand.getBookPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Price cannot be less than zero");
        }
        if(createBookCommand.getBookQuantity() <= 0){
            throw new IllegalArgumentException("Quantity cannot be less than or equal to zero");
        }

        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreatedEvent);
        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @CommandHandler
    public void BookAggregateEdit(EditBookCommand editBookCommand){
        if(editBookCommand.getBookName() == null || editBookCommand.getBookName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(editBookCommand.getBookDescription() == null || editBookCommand.getBookDescription().isBlank()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if(editBookCommand.getBookPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Price cannot be less than zero");
        }

        BookEditedEvent bookEditedEvent = new BookEditedEvent();
        BeanUtils.copyProperties(editBookCommand, bookEditedEvent);
        AggregateLifecycle.apply(bookEditedEvent);
    }

    @CommandHandler
    public void BookAggregateDelete(DeleteBookCommand deleteBookCommand){
        BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);
        AggregateLifecycle.apply(bookDeletedEvent);
    }

    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent){
        this.bookId = bookCreatedEvent.getBookId();
        this.bookName = bookCreatedEvent.getBookName();
        this.bookDescription = bookCreatedEvent.getBookDescription();
        this.bookType = bookCreatedEvent.getBookType();
        this.bookQuantity = bookCreatedEvent.getBookQuantity();
        this.bookPrice = bookCreatedEvent.getBookPrice();
        this.checkOutType = bookCreatedEvent.getCheckOutType();
        this.ownerId = bookCreatedEvent.getOwnerId();
    }

    @EventSourcingHandler
    public void on(BookEditedEvent bookEditedEvent){
        this.bookId = bookEditedEvent.getBookId();
        this.bookName = bookEditedEvent.getBookName();
        this.bookDescription = bookEditedEvent.getBookDescription();
        this.bookType = bookEditedEvent.getBookType();
        this.bookQuantity = bookEditedEvent.getBookQuantity();
        this.bookPrice = bookEditedEvent.getBookPrice();
        this.checkOutType = bookEditedEvent.getCheckOutType();
        this.ownerId = bookEditedEvent.getOwnerId();
    }

    @EventSourcingHandler
    public void on(BookDeletedEvent bookDeletedEvent){
        this.bookId = bookDeletedEvent.getBookId();
    }
}
