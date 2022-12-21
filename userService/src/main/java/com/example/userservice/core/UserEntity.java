package com.example.userservice.core;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 176101681361934315L;
    @Id
    @Column(unique = true)
    private String userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String role;
}
