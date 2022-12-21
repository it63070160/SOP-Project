package com.example.authService.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User implements Serializable{

    private String userId;
    private String username;
    private String password;
    private String role;
    private String email;
    private String address;
    private String ownBook;
}
