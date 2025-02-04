package com.app.prueba.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.app.prueba.models.User;
import com.app.prueba.services.AuthService;
import com.app.prueba.validations.ValidateUser;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        ValidateUser uniqueUser = new ValidateUser();
        if (uniqueUser.getErrorResponse(bindingResult) != null) {
            return uniqueUser.getErrorResponse(bindingResult);
        }

        try {
            return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMostSpecificCause().getMessage().split("Detail: ")[1]);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Map<String, Object> response = authService.login(user);
        if (response.containsKey("message")) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
