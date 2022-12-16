package com.example.userservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CreateBookCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final String username;
    private final String password;
    private final String email;
    private final String address;
    private final String role;
    private final String ownBook;
}
