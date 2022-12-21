package com.example.orderservice.command.rest;

import com.example.orderservice.command.CreateOrderCommand;
import com.example.orderservice.command.DeleteOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
//@CrossOrigin("http://127.0.0.1:5500/")
public class OrderCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody CreateOrderRestModel model){
        CreateOrderCommand command = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .buyer(model.getBuyer())
                .bookList(model.getBookList())
                .total(model.getTotal())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @DeleteMapping
    public String deleteOrder(@RequestBody DeleteOrderRestModel model){
        DeleteOrderCommand command = DeleteOrderCommand.builder()
                .orderId(model.getId())
                .build();
        System.out.println(model);
//        List<HashMap<String, String>> =
//        rabbitTemplate.convertAndSend("MyDirectExchange", "deleteOrder", model.getBookList().toString());
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
//        return "OK";
    }
}
