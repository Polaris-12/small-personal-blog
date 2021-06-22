package com.zhiqian.service;

import com.zhiqian.pojo.User;

public interface UserService {

    //根据用户名和密码查询用户
    User checkUser(String username,String password);

    int getUserCount();

    int update(User user);

    User getUser();
}
