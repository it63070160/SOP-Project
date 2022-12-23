package com.example.bookservice.services;

import com.example.bookservice.command.EditBookCommand;
import com.example.bookservice.core.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class bookService{
    @Autowired
    BookRepository bookRepository;

    private final CommandGateway commandGateway;

    @Autowired
    public bookService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RabbitListener(queues = "createQueueOrder")
    public HashMap<String, String> reBookQuantity(String orders){
        JsonArray json = new JsonParser().parse(orders).getAsJsonArray();
        HashMap<String, String> res = new HashMap<>();
        for (int i=0; i<json.size(); i++){
            String bookId = String.valueOf(new JsonParser().parse(json.get(i).toString()).getAsJsonObject().get("bookId")).replace("\"", "");
            int quantity = Integer.parseInt(String.valueOf(new JsonParser().parse(json.get(i).toString()).getAsJsonObject().get("orderQuantity")));
            BookEntity target = bookRepository.findByBookId(bookId);
            if(target == null || target.getBookQuantity() - quantity < 0) {
                res.put("status", "err");
                return res;
            }
        }
        for (int i=0; i<json.size(); i++){
            String bookId = String.valueOf(new JsonParser().parse(json.get(i).toString()).getAsJsonObject().get("bookId")).replace("\"", "");
            int quantity = Integer.parseInt(String.valueOf(new JsonParser().parse(json.get(i).toString()).getAsJsonObject().get("orderQuantity")));
            BookEntity target = bookRepository.findByBookId(bookId);
            EditBookCommand command = EditBookCommand.builder()
                    .bookId(target.getBookId())
                    .bookName(target.getBookName())
                    .bookDescription(target.getBookDescription())
                    .bookType(target.getBookType())
                    .bookQuantity(target.getBookQuantity() - quantity)
                    .bookPrice(target.getBookPrice())
                    .checkOutType(target.getCheckOutType())
                    .ownerId(target.getOwnerId())
                    .build();
            commandGateway.sendAndWait(command);
        }
        res.put("status", "ok");
        return res;
    }
    
    @RabbitListener(queues = "getBookQueue")
    public String getBookName(String bookId){
        BookEntity userBook = bookRepository.findByBookId(bookId);
        String name = userBook.getBookName();
        System.out.println(name);
        return name;
    }

    @RabbitListener(queues = "deleteQueue")
    public void deleteBook(String userId){
        System.out.println(userId);
        List<BookEntity> userBook = bookRepository.findByOwnerId(userId);
        System.out.println(userBook);
        for(int i=0;i<=userBook.size()-1; i++){
            bookRepository.delete(userBook.get(i));
        }
    }

    @RabbitListener(queues = "deleteQueueOrder")
    public HashMap<String, String> deleteBook2(String orders){
        JsonArray json = new JsonParser().parse(orders).getAsJsonArray();
        HashMap<String, String> res = new HashMap<>();
        for (int i=0; i<json.size(); i++){
            String bookId = String.valueOf(new JsonParser().parse(json.get(i).toString()).getAsJsonObject().get("bookId")).replace("\"", "");
            if(bookRepository.findByBookId(bookId) == null) {
                res.put("status", "err");
                return res;
            }
        }
        for (int i=0; i<json.size(); i++){
            String bookId = String.valueOf(new JsonParser().parse(json.get(i).toString()).getAsJsonObject().get("bookId")).replace("\"", "");
            int quantity = Integer.parseInt(String.valueOf(new JsonParser().parse(json.get(i).toString()).getAsJsonObject().get("orderQuantity")));
            BookEntity target = bookRepository.findByBookId(bookId);
            EditBookCommand command = EditBookCommand.builder()
                    .bookId(target.getBookId())
                    .bookName(target.getBookName())
                    .bookDescription(target.getBookDescription())
                    .bookType(target.getBookType())
                    .bookQuantity(target.getBookQuantity() + quantity)
                    .bookPrice(target.getBookPrice())
                    .checkOutType(target.getCheckOutType())
                    .ownerId(target.getOwnerId())
                    .build();
            commandGateway.sendAndWait(command);
        }
        res.put("status", "ok");
        return res;
    }

}
