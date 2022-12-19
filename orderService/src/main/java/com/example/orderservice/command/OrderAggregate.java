package com.example.orderservice.command;

import com.example.orderservice.core.UserOrderedEntity;
import com.example.orderservice.core.event.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String buyer;
    private List<UserOrderedEntity> bookList;
    private BigDecimal total;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand){
        //validation
        if(createOrderCommand.getBuyer().isBlank() || createOrderCommand.getBuyer() == null){
            throw new IllegalArgumentException("Buyer cannot be empty");
        }
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        this.orderId = orderCreatedEvent.getOrderId();
        this.buyer = orderCreatedEvent.getBuyer();
        this.bookList = orderCreatedEvent.getBookList();
        this.total = orderCreatedEvent.getTotal();
    }
}
