package com.example.userservice.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindUserByIdQuery {
    private String id;
}
