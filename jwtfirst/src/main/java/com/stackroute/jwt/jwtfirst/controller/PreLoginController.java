package com.stackroute.jwt.jwtfirst.controller;


import com.stackroute.jwt.jwtfirst.domain.Response;
import com.stackroute.jwt.jwtfirst.model.User;
import com.stackroute.jwt.jwtfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreLoginController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/registration")
    public ResponseEntity<Response> registration(@RequestBody User user) {
       user.setRole("USER");
//        System.out.println(user.getRole());
        User dbUser = userService.save(user);

        if (dbUser != null) {
            return new ResponseEntity<Response>(new Response("User is saved successfully"), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping(value = "/registrationOwner")
    public ResponseEntity<Response> registrationAdmin(@RequestBody User user) {
        user.setRole("OWNER");
        System.out.println(user.getRole());
        User dbUser = userService.save(user);

        if (dbUser != null) {
            return new ResponseEntity<Response>(new Response("User is saved successfully"), HttpStatus.OK);
        }
        return null;
    }

}



