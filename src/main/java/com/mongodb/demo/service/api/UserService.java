package com.mongodb.demo.service.api;

import com.mongodb.demo.form.user.UserForm;
import com.mongodb.demo.model.common.Result;


public interface UserService {
    Result add(UserForm userForm);

    Result list();

    Result findById(String id);

    Result findByName(String name);

    Result updateUserById(UserForm user);
}
