package com.example.orderservice.core;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -748558978387315194L;
    @Id
    @Column(unique = true)
    private String orderId;
    private String buyer;
    private String buyerAddress;
    @ElementCollection
    @CollectionTable(
            name = "user_ordered",
            joinColumns = @JoinColumn(name = "ORDER_ID")
    )
    private List<UserOrderedEntity> bookList;
    private BigDecimal total;
}
