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

    @RequestMapping("/{userId}")
    public Usery getUser(@PathVariable String userId) {
        return userService.getUser(Integer.parseInt(userId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    public void updateUser(@RequestBody Usery usery, @PathVariable String userId) {
        userService.updateUser(Integer.parseInt(userId), usery);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(Integer.parseInt(userId));
    }
}
