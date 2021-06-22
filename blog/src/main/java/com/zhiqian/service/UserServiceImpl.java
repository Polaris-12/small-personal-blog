package com.zhiqian.service;

import com.zhiqian.mapper.UserMapper;
import com.zhiqian.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        return userMapper.findByUsernameAndPassword(username,password);
    }

    @Override
    public int getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User getUser() {
        User user = userMapper.getUser();
        user.setPassword(null);
        return user;
    }
}
