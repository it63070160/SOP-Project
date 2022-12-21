package com.example.bookservice.command.rest;

import com.example.bookservice.command.CreateBookCommand;
import com.example.bookservice.command.DeleteBookCommand;
import com.example.bookservice.command.EditBookCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
//@CrossOrigin("http://127.0.0.1:5500/")
public class BookCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    public BookCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createBook(@RequestBody CreateBookRestModel model){
        CreateBookCommand command = CreateBookCommand.builder()
                .bookId(UUID.randomUUID().toString())
                .bookName(model.getBookName())
                .bookDescription(model.getBookDescription())
                .bookType(model.getBookType())
                .bookQuantity(model.getBookQuantity())
                .bookPrice(model.getBookPrice())
                .checkOutType(model.getCheckOutType())
                .ownerId(model.getOwnerId())
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
    public String editBook(@RequestBody EditBookRestModel model){
        EditBookCommand command = EditBookCommand.builder()
                .bookId(model.getBookId())
                .bookName(model.getBookName())
                .bookDescription(model.getBookDescription())
                .bookType(model.getBookType())
                .bookQuantity(model.getBookQuantity())
                .bookPrice(model.getBookPrice())
                .checkOutType(model.getCheckOutType())
                .ownerId(model.getOwnerId())
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
    public String deleteBook(@RequestBody DeleteBookRestModel model){
        DeleteBookCommand command = DeleteBookCommand.builder()
                .bookId(model.getId())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
