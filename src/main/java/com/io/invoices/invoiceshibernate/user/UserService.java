package com.io.invoices.invoiceshibernate.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.save(user);
            return;
        }
        throw new IllegalArgumentException("User already exists!");
    }

    public User getUser(String name) {
        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()) {
            throw new IllegalArgumentException("User does not exist!");
        }
        return users.get(0);
    }

    public void updateUser(String userName, User user) {
        List<User> users = userRepository.findByName(userName);

        if (users.isEmpty()) {
            throw new IllegalArgumentException("User does not exist!");
        }

        User updatedUser = users.get(0);

        user.setId(users.get(0).getId());
        user.setName(users.get(0).getName());
        userRepository.save(user);
    }
}
