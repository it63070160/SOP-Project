package com.example.orderservice.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindOrderByBuyerQuery {
    private String id;
}
