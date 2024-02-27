package com.example.schooljpa.controllers;

import com.example.schooljpa.entities.User;
import com.example.schooljpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService ;
    //Affiche la liste des utilisateurs
    @GetMapping("/users/{username}")
    public User user (@PathVariable String username){
        User user = userService.findUserByUserName(username);
        return user ;
    }
}
