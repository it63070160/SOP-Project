package com.example.userservice.core.event;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserCreatedEvent {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String role;
    private String ownBook;
}
