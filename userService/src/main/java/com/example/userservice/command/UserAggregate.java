package com.example.userservice.command;

import com.example.userservice.core.event.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String role;
    private String ownBook;

    public UserAggregate(){}

    @CommandHandler
    public UserAggregate(CreateBookCommand createBookCommand){
        if(createBookCommand.getUsername() == null || createBookCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Error: Username cannot be empty");
        }
        if(createBookCommand.getPassword() == null || createBookCommand.getPassword().isBlank()){
            throw new IllegalArgumentException("Error: Password cannot be empty");
        }
//        if(createBookCommand.getBookType() != "E-Book" || createBookCommand.getBookType() != "Book"){
//            throw new IllegalArgumentException("Book Type not allowed (E-book, Book)");
//        }
//        if(createBookCommand.getCheckOutType() != "COD" || createBookCommand.getCheckOutType() != "Transfer"){
//            throw new IllegalArgumentException("Transfer Type not allowed (Cash on Delivery, Transfer)");
//        }
        if(createBookCommand.getEmail() == null || createBookCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Error: Email cannot be empty");
        }

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(createBookCommand, userCreatedEvent);
        AggregateLifecycle.apply(userCreatedEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent){
        this.userId = userCreatedEvent.getUserId();
        this.username = userCreatedEvent.getUsername();
        this.password = userCreatedEvent.getPassword();
        this.email = userCreatedEvent.getEmail();
        this.address = userCreatedEvent.getAddress();
        this.role = userCreatedEvent.getRole();
        this.ownBook = userCreatedEvent.getOwnBook();
    }
}
