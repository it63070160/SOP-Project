package com.example.productservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteBookCommand {

    @TargetAggregateIdentifier
    private final String bookId;
}
