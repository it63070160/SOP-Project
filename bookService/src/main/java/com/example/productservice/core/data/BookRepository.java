package com.example.productservice.core.data;

import com.example.productservice.core.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String> {
    BookEntity findByBookId(String bookId);
}
