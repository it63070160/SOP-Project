package com.example.userservice.query.rest;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserRestModel {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String role;
    private String ownBook;
}
