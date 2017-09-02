package com.mongodb.demo.controller;

import com.mongodb.demo.model.common.Result;
import com.mongodb.demo.model.user.User;
import com.mongodb.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public Result addUser(@RequestParam(value = "name") String name,
                          @RequestParam(value = "phone") String phone) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        userService.add(user);
        return Result.setCodeMsgData("000000", "添加成功", user.getId());
    }

    @GetMapping
    public Result getUserList() {
        return Result.setCodeMsgData("000000", "查询成功", userService.list());
    }

    @GetMapping(value = "/{id}")
    public Result getUserById(@PathVariable String id) {
        return Result.setCodeMsgData("000000", "查询成功", userService.findById(id));
    }
}
