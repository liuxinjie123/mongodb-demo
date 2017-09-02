package com.mongodb.demo.repository;

import com.mongodb.demo.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findUsersByNameOrderByCreateTimeDesc(String name);



}
