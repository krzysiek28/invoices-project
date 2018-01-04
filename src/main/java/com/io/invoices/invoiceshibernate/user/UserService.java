package com.io.invoices.invoiceshibernate.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Usery getUser(Integer userId) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("Usery does not exist!");
        }

        return userRepository.findOne(userId);
    }

    public void addUser(Usery usery) {
        if (userRepository.findByName(usery.getName()).isEmpty()) {
            userRepository.save(usery);
            return;
        }
        throw new IllegalArgumentException("Usery already exists!");
    }


    public void updateUser(Integer userId, Usery usery) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("User with provided id does not exist!");
        }

        Usery dbUser = userRepository.findOne(userId);
        dbUser.setName(usery.getName());
        dbUser.setEmail(usery.getEmail());
        dbUser.setPassword(usery.getPassword());
        dbUser.setRole(usery.getRole());
        userRepository.save(dbUser);
    }

    public void deleteUser(Integer userId) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("User does not exist!");
        }

        userRepository.delete(userId);
    }
}
