package com.example.userservice.command.rest;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateUserRestModel {
    private String username;
    private String password;
    private String email;
    private String address;
    private String role;
}
