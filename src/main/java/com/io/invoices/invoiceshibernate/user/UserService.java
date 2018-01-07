package com.io.invoices.invoiceshibernate.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    ApplicationUserRepository userRepository;

    public ApplicationUser getUser(Integer userId) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("Usery does not exist!");
        }

        return userRepository.findOne(userId);
    }

    public void updateUser(Integer userId, ApplicationUser usery) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("User with provided id does not exist!");
        }

        ApplicationUser dbUser = userRepository.findOne(userId);
        dbUser.setUsername(usery.getUsername());
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
