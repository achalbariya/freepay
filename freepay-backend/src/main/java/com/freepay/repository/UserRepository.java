package com.freepay.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.freepay.model.UserDao;
public interface UserRepository extends CrudRepository<UserDao, Long> {
    UserDao findByUsername(String username);
    List<UserDao> findAll();
}