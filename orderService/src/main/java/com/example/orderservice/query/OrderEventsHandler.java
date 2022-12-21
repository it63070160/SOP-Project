package com.example.orderservice.query;

import com.example.orderservice.core.OrderEntity;
import com.example.orderservice.core.data.OrderRepository;
import com.example.orderservice.core.event.OrderCreatedEvent;
import com.example.orderservice.core.event.OrderDeletedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event){
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        orderRepository.save(orderEntity);
    }

    @EventHandler
    public void on(OrderDeletedEvent event){
        OrderEntity bookEntity = new OrderEntity();
        BeanUtils.copyProperties(event, bookEntity);
        orderRepository.delete(bookEntity);
    }
}
