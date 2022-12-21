package com.example.bookservice.core;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
public class BookEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 176101681361934315L;
    @Id
    @Column(unique = true)
    private String bookId;
    private String bookName;
    private String bookDescription;
    private String bookType;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
    private String checkOutType;
    private String ownerId;
}
