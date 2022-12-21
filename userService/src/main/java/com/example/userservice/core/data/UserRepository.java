package com.example.userservice.core.data;

import com.example.userservice.core.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserId(String userId);
    List<UserEntity> findByUsernameAndPassword(String username, String password);
}
