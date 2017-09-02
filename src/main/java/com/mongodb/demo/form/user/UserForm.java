package com.mongodb.demo.form.user;

import com.mongodb.demo.form.user.validate.AddUser;
import com.mongodb.demo.form.user.validate.UpdateUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserForm implements Serializable {
    @NotBlank(message = "id不能为空", groups = {UpdateUser.class})
    private String id;
    @NotBlank(message = "姓名不能为空", groups = {AddUser.class})
    private String name;
    @NotBlank(message = "手机号不能为空", groups = {AddUser.class})
    private String phone;
}
