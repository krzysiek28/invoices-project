package com.io.invoices.invoiceshibernate.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(Usery usery) {
        if (userRepository.findByName(usery.getName()).isEmpty()) {
            userRepository.save(usery);
            return;
        }
        throw new IllegalArgumentException("Usery already exists!");
    }

    public Usery getUser(String name) {
        List<Usery> useries = userRepository.findByName(name);
        if (useries.isEmpty()) {
            throw new IllegalArgumentException("Usery does not exist!");
        }
        return useries.get(0);
    }

    public void updateUser(String userName, Usery usery) {
        List<Usery> useries = userRepository.findByName(userName);

        if (useries.isEmpty()) {
            throw new IllegalArgumentException("Usery does not exist!");
        }

        Usery updatedUsery = useries.get(0);
        usery.setId(useries.get(0).getId());
        usery.setName(useries.get(0).getName());
        userRepository.save(usery);
    }

    public void deleteUser(String userName) {
        List<Usery> useries = userRepository.findByName(userName);
        if (useries.isEmpty()) {
            throw new IllegalArgumentException("Usery does not exist!");
        }
        userRepository.delete(useries.get(0).getId());
    }
}
