package com.example.authService.services;

import com.example.authService.entities.AuthRequest;
import com.example.authService.entities.AuthResponse;
import com.example.authService.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AuthService {
    private final JwtUtil jwt;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AuthService(final JwtUtil jwt) { this.jwt = jwt; }

    public AuthResponse logIn(AuthRequest authRequest){
//        check email and password here

        HashMap<String, String> check = new HashMap<String, String>();
        check.put("username", authRequest.getUsername());
        check.put("password", authRequest.getPassword());
        Object result = rabbitTemplate.convertSendAndReceive("MyDirectExchange", "login", check);
//        System.out.println("OK rabbit completed.");
//        System.out.println("OK rabbit got : " + result);

        if(result != null){
            HashMap<String, String> res = (HashMap<String, String>) result;
            User user = User.builder()
                    .userId(res.get("userId"))
                    .username(res.get("username"))
                    .password(res.get("password"))
                    .role(res.get("role"))
                    .address(res.get("address"))
                    .email(res.get("email"))
                    .ownBook(res.get("ownBook"))
                    .build();
            String accessToken = jwt.generate(user, "ACCESS");
            String refreshToken = jwt.generate(user, "REFRESH");
            return new AuthResponse(accessToken, refreshToken);
        }
        else{
            return new AuthResponse("Wrong username or password.", "Wrong username or password.");
        }

    }
}
