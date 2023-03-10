package com.example.userservice.command.rest;

import com.example.userservice.command.CreateUserCommand;
import com.example.userservice.command.DeleteUserCommand;
import com.example.userservice.command.EditUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UserCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserRestModel model){
        CreateUserCommand command = CreateUserCommand.builder()
                .userId(UUID.randomUUID().toString())
                .username(model.getUsername())
                .password(model.getPassword())
                .email(model.getEmail())
                .address(model.getAddress())
                .role(model.getRole())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping
    public String editUser(@RequestBody EditUserRestModel model){
        EditUserCommand command = EditUserCommand.builder()
                .userId(model.getId())
                .username(model.getUsername())
                .email(model.getEmail())
                .address(model.getAddress())
                .role(model.getRole())
                .password(model.getPassword())
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
    public String deleteUser(@RequestBody DeleteUserRestModel model) {
        DeleteUserCommand command = DeleteUserCommand.builder()
                .userId(model.getId())
                .build();
        rabbitTemplate.convertAndSend("MyDirectExchange","deleteUser", model.getId());
        String result;
        try {
            result = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
