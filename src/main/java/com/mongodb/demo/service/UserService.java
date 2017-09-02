package com.mongodb.demo.service;

import com.mongodb.demo.form.user.UserForm;
import com.mongodb.demo.model.common.Constants;
import com.mongodb.demo.model.common.Result;
import com.mongodb.demo.dao.user.UserDao;
import com.mongodb.demo.model.user.UserModel;
import com.mongodb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Result add(UserForm user) {
        UserDao userDao = new UserDao();
        userDao.setName(user.getName());
        userDao.setPhone(user.getPhone());
        userDao.setCreateTime(LocalDateTime.now());
        userDao.setLastUpdateTime(LocalDateTime.now());
        try {
            userRepository.save(userDao);
            return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.ADD_OK_MSG, userDao);
        } catch (Exception e) {
            return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.ADD_ERROR_MSG);
        }
    }

    public List<UserDao> list () {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        List<UserDao> userDaoList = userRepository.findAll(sort);
        return userDaoList;
    }

    public UserDao findById(String id) {
        return userRepository.findOne(id);
    }

    public List<UserDao> findByName(String name) {
        return userRepository.findUsersByNameOrderByCreateTimeDesc(name);
    }

    public Result updateUserById(UserForm user) {
        UserDao userDao = userRepository.findOne(user.getId());
        if (userDao == null) return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.UPDATE_ERROR_MSG);
        if (!StringUtils.isEmpty(user.getName())) {
            userDao.setName(user.getName());
        }
        if (!StringUtils.isEmpty(user.getPhone())) {
            userDao.setPhone(user.getPhone());
        }
        userDao.setLastUpdateTime(LocalDateTime.now());
        try {
            userRepository.save(userDao);
            return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.UPDATE_OK_MSG, userDao);
        } catch (Exception e) {
            return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.UPDATE_ERROR_MSG);
        }
    }
}
