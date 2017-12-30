package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.user.Users;
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
    public List<Users> getUsers(){
        return userService.getUsers();
    }

//    @GetMapping (value = "/{id}")
//    public Users getUserByName(@PathVariable Integer id){
//        return userService.getUserById(id);
//    }

}
