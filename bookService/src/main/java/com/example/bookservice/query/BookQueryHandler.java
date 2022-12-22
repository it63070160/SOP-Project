package com.example.bookservice.query;

import com.example.bookservice.core.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import com.example.bookservice.query.rest.BookRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookQueryHandler {
    private final BookRepository bookRepository;

    public BookQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @QueryHandler
    List<BookRestModel> findBooks(FindBooksQuery query){
        List<BookRestModel> booksRest = new ArrayList<>();
        List<BookEntity> storedBooks = bookRepository.findAll();
        for(BookEntity bookEntity : storedBooks){
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            booksRest.add(bookRestModel);
        }
        return booksRest;
    }

    @QueryHandler
    BookRestModel findBookById(FindBookByIdQuery query){
        BookEntity book = bookRepository.findByBookId(query.getId());
        BookRestModel bookRestModel = new BookRestModel();
        BeanUtils.copyProperties(book, bookRestModel);
        return bookRestModel;
    }
}