package com.ny.service.impl;

import com.ny.dao.UserDao;
import com.ny.po.User;
import com.ny.service.UserService;
import com.ny.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }
}
