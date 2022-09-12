package com.example.authorizationservice.controller;

import com.example.authorizationservice.exeptions.InvalidCredentials;
import com.example.authorizationservice.exeptions.UnauthorizedUser;
import com.example.authorizationservice.model.Authorities;
import com.example.authorizationservice.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> icHandler(InvalidCredentials exp) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> UUHandler(UnauthorizedUser exp) {
        System.out.println(exp.getMessage());
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
