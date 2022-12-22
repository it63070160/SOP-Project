package com.example.orderservice.query.rest;

import com.example.orderservice.query.FindOrderByBuyerQuery;
import com.example.orderservice.query.FindOrdersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
//@CrossOrigin("http://127.0.0.1:5500/")
public class OrderQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<OrderRestModel> getOrders(){
        FindOrdersQuery findOrdersQuery = new FindOrdersQuery();
        List<OrderRestModel> orders = queryGateway
                .query(findOrdersQuery, ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
//        Hibernate.initialize(orders);
        return orders;
    }

    @RequestMapping(value = "/orderbybuyer", method = RequestMethod.GET)
    public List<OrderRestModel> userGetOrders(@RequestParam("id") String id){
        FindOrderByBuyerQuery findOrderByBuyerQuery = FindOrderByBuyerQuery.builder()
                .id(id)
                .build();
        return queryGateway.query(findOrderByBuyerQuery, ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();

    }
}
