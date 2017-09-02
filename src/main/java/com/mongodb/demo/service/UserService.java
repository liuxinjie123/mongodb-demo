package com.mongodb.demo.service;

import com.mongodb.demo.model.common.Constants;
import com.mongodb.demo.model.common.Result;
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

    public List<User> findByName(String name) {
        return userRepository.findUsersByName(name);
    }

    public Result updateUserById(String id) {
        User user = userRepository.findOne(id);
        if (user == null) return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.UPDATE_ERROR_MSG);
        user.setName(user.getName() + "_" + id.substring(id.length() - 2, id.length()));
        try {
            userRepository.save(user);
            return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.UPDATE_OK_MSG, user);
        } catch (Exception e) {
            return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.UPDATE_ERROR_MSG);
        }
    }
}
