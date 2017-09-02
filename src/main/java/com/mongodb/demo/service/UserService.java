package com.mongodb.demo.service;

import com.mongodb.demo.model.user.User;
import com.mongodb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void add(User emp) {
        userRepository.save(emp);
    }

    public List<User> list () {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findOne(id);
    }
}
