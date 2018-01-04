package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.user.Usery;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public void addUser(@RequestBody Usery usery) {
        userService.addUser(usery);
    }

    @RequestMapping("/{name}")
    public Usery getUser(@PathVariable String name) {
        return userService.getUser(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userName}")
    public void updateUser(@RequestBody Usery usery, @PathVariable String userName) {
        userService.updateUser(userName, usery);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
    }
}
