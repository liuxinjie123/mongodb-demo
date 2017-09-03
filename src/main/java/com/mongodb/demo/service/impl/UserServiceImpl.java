package com.mongodb.demo.service.impl;

import com.mongodb.demo.config.support.LocalDateTimeDeserializer;
import com.mongodb.demo.form.user.UserForm;
import com.mongodb.demo.model.common.Constants;
import com.mongodb.demo.model.common.Result;
import com.mongodb.demo.dao.user.UserDao;
import com.mongodb.demo.model.user.UserModel;
import com.mongodb.demo.repository.UserRepository;
import com.mongodb.demo.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(LocalDateTimeDeserializer.class);

    @Autowired
    private UserRepository userRepository;

    @Override
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

    @Override
    public Result list () {
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            List<UserDao> userDaoList = userRepository.findAll(sort);
            return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.QUERY_OK_MSG, userDaoList);
        } catch (Exception e) {
            return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.QUERY_ERROR_MSG);
        }
    }

    @Override
    public Result findById(String id) {
        try {
            UserDao userDao = userRepository.findOne(id);
            return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.QUERY_OK_MSG, userDao);
        } catch (Exception e) {
            return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.QUERY_ERROR_MSG);
        }
    }

    @Override
    public Result findByName(String name) {
        try {
            List<UserDao> userDaoList = userRepository.findUsersByNameOrderByCreateTimeDesc(name);
            return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.QUERY_OK_MSG, userDaoList);
        } catch (Exception e) {
            return Result.setCodeMsg(Constants.RETURN_ERROR_CODE, Constants.QUERY_ERROR_MSG);
        }
    }

    @Override
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
