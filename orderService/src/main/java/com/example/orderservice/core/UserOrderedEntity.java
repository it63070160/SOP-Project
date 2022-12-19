package com.example.orderservice.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "user_ordered")
@Data
@Embeddable
public class UserOrderedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 4136807205786851428L;
    private String bookId;
    private Integer orderQuantity;
}
