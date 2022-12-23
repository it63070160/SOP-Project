package com.example.userservice.command;

import com.example.userservice.core.event.UserCreatedEvent;
import com.example.userservice.core.event.UserDeletedEvent;
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

    public UserAggregate(){}

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        if(createUserCommand.getUsername() == null || createUserCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Error: Username cannot be empty");
        }
        if(createUserCommand.getPassword() == null || createUserCommand.getPassword().isBlank()){
            throw new IllegalArgumentException("Error: Password cannot be empty");
        }
        if(createUserCommand.getEmail() == null || createUserCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Error: Email cannot be empty");
        }
        if(createUserCommand.getRole() == null || createUserCommand.getRole().isBlank()){
            throw new IllegalArgumentException("Error: Role must be select");
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
        if(editUserCommand.getEmail() == null || editUserCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Error: Email cannot be empty");
        }

        UserEditedEvent userEditedEvent = new UserEditedEvent();
        BeanUtils.copyProperties(editUserCommand, userEditedEvent);
        AggregateLifecycle.apply(userEditedEvent);
    }

    @CommandHandler
    public void UserAggregateDelete(DeleteUserCommand deleteUserCommand){
        UserDeletedEvent userDeletedEvent = new UserDeletedEvent();
        BeanUtils.copyProperties(deleteUserCommand, userDeletedEvent);
        AggregateLifecycle.apply(userDeletedEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent){
        this.userId = userCreatedEvent.getUserId();
        this.username = userCreatedEvent.getUsername();
        this.password = userCreatedEvent.getPassword();
        this.email = userCreatedEvent.getEmail();
        this.address = userCreatedEvent.getAddress();
        this.role = userCreatedEvent.getRole();
    }

    @EventSourcingHandler
    public void on(UserEditedEvent userEditedEvent){
        this.userId = userEditedEvent.getUserId();
        this.username = userEditedEvent.getUsername();
        this.password = userEditedEvent.getPassword();
        this.email = userEditedEvent.getEmail();
        this.address = userEditedEvent.getAddress();
        this.role = userEditedEvent.getRole();
    }

    @EventSourcingHandler
    public void on(UserDeletedEvent userDeletedEvent){
        this.userId = userDeletedEvent.getUserId();
    }
}
