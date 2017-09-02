package com.mongodb.demo.repository;

import com.mongodb.demo.dao.user.UserDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserDao, String> {

    List<UserDao> findUsersByNameOrderByCreateTimeDesc(String name);



}
