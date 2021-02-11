package com.example.springboot.service;

import com.example.springboot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getUser(Long id);
    User findByUserName(String email);
}
