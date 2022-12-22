package com.example.bookservice.query.rest;

import com.example.bookservice.query.FindBookByIdQuery;
import com.example.bookservice.query.FindBooksQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value = "/{userId}")
    public List<BookRestModel> getUserBooks(@PathVariable String userId){
        FindBooksQuery findBooksQuery = new FindBooksQuery();
        List<BookRestModel> books = queryGateway
                .query(findBooksQuery, ResponseTypes.multipleInstancesOf(BookRestModel.class)).join();
        List<BookRestModel> userBooks = new ArrayList<>();
        userBooks.clear();
        for(BookRestModel book : books){
            if(book.getOwnerId().equals(userId)){
                userBooks.add(book);
            }
        }
        return userBooks;
    }

    @RequestMapping(value = "/bookbybookid", method = RequestMethod.GET)
    public BookRestModel getBookById(@RequestParam("id") String id) {
        FindBookByIdQuery findBookByIdQuery = FindBookByIdQuery.builder().id(id).build();
        return queryGateway.query(findBookByIdQuery, ResponseTypes.instanceOf(BookRestModel.class)).join();
    }
}