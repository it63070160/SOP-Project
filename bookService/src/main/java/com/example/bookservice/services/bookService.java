package com.example.bookservice.services;

import com.example.bookservice.core.BookEntity;
import com.example.bookservice.core.data.BookRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bookService{
    @Autowired
    BookRepository bookRepository;

    @RabbitListener(queues = "deleteQueue")
    public void deleteBook(String userId){
        System.out.println(userId);
        List<BookEntity> userBook = bookRepository.findByOwnerId(userId);
        System.out.println(userBook);
        for(int i=0;i<=userBook.size()-1; i++){
            bookRepository.delete(userBook.get(i));
        }
    }

//    @RabbitListener(queues = "deleteQueueOrder")
//    public void deleteBook2(String orders){
//        System.out.println(orders);
//        List<BookEntity> orderBook = bookRepository.findByBookId(orders.get(0).get(()))
//    }

}
