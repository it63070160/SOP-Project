package com.example.userservice.services;

import com.example.userservice.core.UserEntity;
import com.example.userservice.core.data.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @RabbitListener(queues = "loginQueue")
    public HashMap<String, String> checkLogin(HashMap<String, String> check){
        System.out.println(check.get("username"));
        System.out.println(check.get("password"));
        List<UserEntity> res = userRepository.findByUsernameAndPassword(check.get("username"), check.get("password"));
        System.out.println("Found : " + res.size());
        if (res.size() != 0){
            System.out.println("Found : " + res.get(0));
            HashMap<String, String> user = new HashMap<>();
            user.put("userId", res.get(0).getUserId());
            user.put("username", res.get(0).getUsername());
            user.put("password", res.get(0).getPassword());
            user.put("email", res.get(0).getEmail());
            user.put("address", res.get(0).getAddress());
            user.put("role", res.get(0).getRole());
            return user;
        }
        else{
            return null;
        }
    }
}