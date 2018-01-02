package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.user.User;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping (value = "/{id}")
    public User getUserByName(@PathVariable Integer id){
        return userService.getUserById(id);
    }

}
