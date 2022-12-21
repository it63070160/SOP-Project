package com.example.bookservice.query.rest;

import com.example.bookservice.query.FindBooksQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
//@CrossOrigin("http://127.0.0.1:5500/")
public class BookQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<BookRestModel> getBooks(){
        FindBooksQuery findBooksQuery = new FindBooksQuery();
        List<BookRestModel> books = queryGateway
                .query(findBooksQuery, ResponseTypes.multipleInstancesOf(BookRestModel.class)).join();
        return books;
    }
}