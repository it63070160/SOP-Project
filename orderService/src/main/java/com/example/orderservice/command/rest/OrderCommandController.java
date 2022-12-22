package com.example.orderservice.command.rest;

import com.example.orderservice.command.CreateOrderCommand;
import com.example.orderservice.command.DeleteOrderCommand;
import com.example.orderservice.services.EmailSenderService;
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
    private EmailSenderService emailSenderService;

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
                .buyerAddress(model.getBuyerAddress())
                .build();
        HashMap<String, String> res = (HashMap<String, String>) rabbitTemplate.convertSendAndReceive("MyDirectExchange", "createOrder", model.getBookList_String());
        if (res.get("status").equals("err")){
            return "Error: Cannot create order";
        }
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        emailSenderService.sendEmail(model.getEmail(), "Order create completed", model, command);
        return result;
    }

    @DeleteMapping
    public String deleteOrder(@RequestBody DeleteOrderRestModel model){
        DeleteOrderCommand command = DeleteOrderCommand.builder()
                .orderId(model.getId())
                .build();
        HashMap<String, String> res = (HashMap<String, String>) rabbitTemplate.convertSendAndReceive("MyDirectExchange", "deleteOrder", model.getBookList());
        if (res.get("status").equals("err")){
            return "Cannot remove order : " + model.getId();
        }
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
