package com.example.orderservice.query;

import com.example.orderservice.core.OrderEntity;
import com.example.orderservice.core.data.OrderRepository;
import com.example.orderservice.query.rest.OrderRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderQueryHandler {
    private final OrderRepository orderRepository;

    public OrderQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @QueryHandler
    List<OrderRestModel> findOrders(FindOrdersQuery query){
        List<OrderRestModel> ordersRest = new ArrayList<>();
        List<OrderEntity> storedOrders = orderRepository.findAll();
        System.out.println(storedOrders); // Remove = Error ??? How
        for(OrderEntity orderEntity : storedOrders){
            OrderRestModel orderRestModel = new OrderRestModel();
//            Hibernate.initialize(orderRestModel.getBookList());
            BeanUtils.copyProperties(orderEntity, orderRestModel);
            ordersRest.add(orderRestModel);
        }
        return ordersRest;
    }

    @QueryHandler
    List<OrderRestModel> findOrdersByBuyer(FindOrderByBuyerQuery query){
        List<OrderRestModel> ordersRest = new ArrayList<>();
        List<OrderEntity> storedOrders = orderRepository.findByBuyer(query.getId());
        System.out.println(storedOrders); // Remove = Error ??? How
        for(OrderEntity orderEntity : storedOrders){
            OrderRestModel orderRestModel = new OrderRestModel();
//            Hibernate.initialize(orderRestModel.getBookList());
            BeanUtils.copyProperties(orderEntity, orderRestModel);
            ordersRest.add(orderRestModel);
        }
        return ordersRest;
    }
}
