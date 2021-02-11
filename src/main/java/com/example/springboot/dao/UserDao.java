package com.example.springboot.dao;

import com.example.springboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getUser(Long id);
    User findByUserName(String email);
}
