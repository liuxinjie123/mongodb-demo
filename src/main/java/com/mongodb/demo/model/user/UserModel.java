package com.mongodb.demo.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserModel implements Serializable {
    private String id;
    private String name;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
}
