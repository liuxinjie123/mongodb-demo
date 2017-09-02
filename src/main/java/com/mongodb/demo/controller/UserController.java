package com.mongodb.demo.controller;

import com.mongodb.demo.form.user.UserForm;
import com.mongodb.demo.form.user.validate.AddUser;
import com.mongodb.demo.form.user.validate.UpdateUser;
import com.mongodb.demo.model.common.Constants;
import com.mongodb.demo.model.common.Result;
import com.mongodb.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public Result addUser(@Validated(value = {AddUser.class}) UserForm user) {
        return userService.add(user);
    }

    @GetMapping
    public Result getUserList() {
        return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.QUERY_OK_MSG, userService.list());
    }

    @GetMapping(value = "/{id}")
    public Result getUserById(@PathVariable String id) {
        return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.QUERY_OK_MSG, userService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    public Result getUserByName(@PathVariable String name) {
        return Result.setCodeMsgData(Constants.RETURN_OK_CODE, Constants.QUERY_OK_MSG, userService.findByName(name));
    }

    @PutMapping
    public Result updateUserById(@Validated(value = {UpdateUser.class}) UserForm user) {
        return userService.updateUserById(user);
    }
}
