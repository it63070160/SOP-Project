package com.example.productservice.command;

import com.example.productservice.core.event.BookCreatedEvent;
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
//        if(createBookCommand.getBookType() != "E-Book" || createBookCommand.getBookType() != "Book"){
//            throw new IllegalArgumentException("Book Type not allowed (E-book, Book)");
//        }
//        if(createBookCommand.getCheckOutType() != "COD" || createBookCommand.getCheckOutType() != "Transfer"){
//            throw new IllegalArgumentException("Transfer Type not allowed (Cash on Delivery, Transfer)");
//        }
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
}
