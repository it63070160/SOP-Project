package com.example.userservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final String username;
    private final String password;
    private final String email;
    private final String address;
    private final String role;
}
