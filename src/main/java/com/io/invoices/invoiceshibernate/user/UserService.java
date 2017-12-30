package com.io.invoices.invoiceshibernate.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(Users users){
        userRepository.save(users);
    }

    public void addUsers(List<Users> users){
        userRepository.save(users);
    }

//    public Users getUserById(Integer id){
//        return userRepository.findOne(id);
//    }

    public List<Users> getUsers(){
        List<Users> users = new ArrayList<>();
        Iterable<Users> iterable = userRepository.findAll();
        iterable.forEach(e -> users.add(e));
        return users;
    }

//    public void deleteUserById(Integer id){
//        userRepository.delete(id);
//    }

}
