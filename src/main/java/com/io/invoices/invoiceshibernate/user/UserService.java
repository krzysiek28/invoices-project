package com.io.invoices.invoiceshibernate.user;

import com.io.invoices.invoiceshibernate.firm.Firm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public void addUsers(List<User> users){
        userRepository.save(users);
    }

    public User getUserById(Integer id){
        return userRepository.findOne(id);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(e -> users.add(e));
        return users;
    }

    public void deleteUserById(Integer id){
        userRepository.delete(id);
    }

}
