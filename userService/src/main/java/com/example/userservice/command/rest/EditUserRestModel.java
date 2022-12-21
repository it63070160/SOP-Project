package com.example.userservice.command.rest;
import lombok.Data;

@Data
public class EditUserRestModel {
    private String id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String role;
}