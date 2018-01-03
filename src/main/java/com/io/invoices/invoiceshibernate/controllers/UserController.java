package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.user.User;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping("/{name}")
    public User getUser(@PathVariable String name) {
        return userService.getUser(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userName}")
    public void updateUser(@RequestBody User user, @PathVariable String userName) {
        userService.updateUser(userName, user);
    }
}
