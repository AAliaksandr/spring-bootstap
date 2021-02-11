package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.UserDao;
import com.example.springboot.model.User;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void add(User user) {
        if (!user.getPassword().isEmpty()){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDao.add(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void edit(User user) {
        if (!user.getPassword().isEmpty()){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        else {
            User userBd = userDao.getUser(user.getId());
            user.setPassword(userBd.getPassword());
        }
        userDao.edit(user);
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public User findByUserName(String email) {
      return userDao.findByUserName(email);
    }
}
