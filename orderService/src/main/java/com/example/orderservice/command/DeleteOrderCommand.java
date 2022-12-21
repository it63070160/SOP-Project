package com.example.orderservice.command;

import com.example.orderservice.core.UserOrderedEntity;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Builder
@Data
public class DeleteOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
}
