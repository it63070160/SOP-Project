package com.example.orderservice.command;

import com.example.orderservice.core.UserOrderedEntity;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
    private final String buyer;
    private final String buyerAddress;
    private final List<UserOrderedEntity> bookList;
    private final BigDecimal total;
}
