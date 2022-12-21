package com.example.bookservice.core.data;

import com.example.bookservice.core.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {
    BookEntity findByBookId(String bookId);

    List<BookEntity> findByOwnerId(String ownerId);
}
