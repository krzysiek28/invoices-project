package com.io.invoices.invoiceshibernate.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ApplicationUserRepository userRepository;

    public UserService(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApplicationUser getUser(Integer userId) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("Uzytkownik nie istnieje");
        }

        return userRepository.findOne(userId);
    }

    public ApplicationUser getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUser(Integer userId, ApplicationUser usery) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("Uzytkownik nie istnieje!");
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
            throw new IllegalArgumentException("Uzytkownik nie istnieje!");
        }

        userRepository.delete(userId);
    }
}
