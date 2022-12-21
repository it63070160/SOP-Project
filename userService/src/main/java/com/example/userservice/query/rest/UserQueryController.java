package com.example.userservice.query.rest;

import com.example.userservice.query.FindUserByIdQuery;
import com.example.userservice.query.FindUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserQueryController {
    @Autowired
    QueryGateway queryGateway;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserRestModel> getUsers(){
        FindUsersQuery findUsersQuery = new FindUsersQuery();
        List<UserRestModel> users = queryGateway
                .query(findUsersQuery, ResponseTypes.multipleInstancesOf(UserRestModel.class)).join();
        return users;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(@RequestHeader(value="id") String userId, @RequestHeader(value="role") String role){
        return "{\"id\":\"" + userId + "\", \"role\" :\"" + role + "\"}";
    }

    @RequestMapping(value = "/userbyid", method = RequestMethod.GET)
    public UserRestModel getUserById(@RequestParam("id") String id){
        FindUserByIdQuery findUserByIdQuery = FindUserByIdQuery.builder()
                .id(id)
                .build();
        return queryGateway.query(findUserByIdQuery, ResponseTypes.instanceOf(UserRestModel.class)).join();
    }
}
