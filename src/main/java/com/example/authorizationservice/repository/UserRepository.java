package com.example.authorizationservice.repository;

import com.example.authorizationservice.model.Authorities;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        if (user.equals("noname")) return List.of();
        return List.of(Authorities.DELETE, Authorities.READ, Authorities.WRITE);
    }
}
