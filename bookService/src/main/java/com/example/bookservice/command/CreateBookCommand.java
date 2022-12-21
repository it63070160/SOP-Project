package com.example.bookservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Builder
@Data
public class CreateBookCommand {

    @TargetAggregateIdentifier
    private final String bookId;
    private final String bookName;
    private final String bookDescription;
    private final String bookType;
    private final Integer bookQuantity;
    private final BigDecimal bookPrice;
    private final String checkOutType;
    private final String ownerId;
}
