package com.example.userservice.command.rest;

import com.example.userservice.command.CreateBookCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://127.0.0.1:5500/")
public class UserCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    public UserCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserRestModel model){
        CreateBookCommand command = CreateBookCommand.builder()
                .userId(UUID.randomUUID().toString())
                .username(model.getUsername())
                .password(model.getPassword())
                .email(model.getEmail())
                .address("")
                .role("User")
                .ownBook(null)
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
    public String deleteBook(){
        return "product deleted";
    }
}
