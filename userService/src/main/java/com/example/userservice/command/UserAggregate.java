package com.example.userservice.command;

import com.example.userservice.core.event.UserCreatedEvent;
import com.example.userservice.core.event.UserEditedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

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
    public UserAggregate(CreateUserCommand createUserCommand){
        if(createUserCommand.getUsername() == null || createUserCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Error: Username cannot be empty");
        }
        if(createUserCommand.getPassword() == null || createUserCommand.getPassword().isBlank()){
            throw new IllegalArgumentException("Error: Password cannot be empty");
        }
//        if(createBookCommand.getBookType() != "E-Book" || createBookCommand.getBookType() != "Book"){
//            throw new IllegalArgumentException("Book Type not allowed (E-book, Book)");
//        }
//        if(createBookCommand.getCheckOutType() != "COD" || createBookCommand.getCheckOutType() != "Transfer"){
//            throw new IllegalArgumentException("Transfer Type not allowed (Cash on Delivery, Transfer)");
//        }
        if(createUserCommand.getEmail() == null || createUserCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Error: Email cannot be empty");
        }

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(createUserCommand, userCreatedEvent);
        AggregateLifecycle.apply(userCreatedEvent);
    }

    @CommandHandler
    public void UserAggregateEdit(EditUserCommand editUserCommand){
        if(editUserCommand.getUsername() == null || editUserCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Error: Username cannot be empty");
        }
//        if(editUserCommand.getPassword() == null || editUserCommand.getPassword().isBlank()){
//            throw new IllegalArgumentException("Error: Password cannot be empty");
//        }
//        if(createBookCommand.getBookType() != "E-Book" || createBookCommand.getBookType() != "Book"){
//            throw new IllegalArgumentException("Book Type not allowed (E-book, Book)");
//        }
//        if(createBookCommand.getCheckOutType() != "COD" || createBookCommand.getCheckOutType() != "Transfer"){
//            throw new IllegalArgumentException("Transfer Type not allowed (Cash on Delivery, Transfer)");
//        }
        if(editUserCommand.getEmail() == null || editUserCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Error: Email cannot be empty");
        }

        UserEditedEvent userEditedEvent = new UserEditedEvent();
        BeanUtils.copyProperties(editUserCommand, userEditedEvent);
        AggregateLifecycle.apply(userEditedEvent);
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

    @EventSourcingHandler
    public void on(UserEditedEvent userEditedEvent){
        this.userId = userEditedEvent.getUserId();
        this.username = userEditedEvent.getUsername();
//        this.password = userEditedEvent.getPassword();
        this.email = userEditedEvent.getEmail();
        this.address = userEditedEvent.getAddress();
        this.role = userEditedEvent.getRole();
//        this.ownBook = userEditedEvent.getOwnBook();
    }
}
