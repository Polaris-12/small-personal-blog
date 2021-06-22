package com.zhiqian;

import com.zhiqian.mapper.BlogMapper;
import com.zhiqian.mapper.TypeMapper;
import com.zhiqian.pojo.Type;
import com.zhiqian.pojo.User;
import com.zhiqian.service.BlogService;
import com.zhiqian.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BlogMapper blogMapper;
    @Test
    void contextLoads() {
    }

}
