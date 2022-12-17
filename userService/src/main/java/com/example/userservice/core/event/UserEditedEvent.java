package com.example.userservice.core.event;

import lombok.Data;

@Data
public class UserEditedEvent {
    private String userId;
    private String username;
//    private String password;
    private String email;
    private String address;
    private String role;
//    private String ownBook;
}
