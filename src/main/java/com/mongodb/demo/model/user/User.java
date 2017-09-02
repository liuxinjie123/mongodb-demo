package com.mongodb.demo.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;

}
