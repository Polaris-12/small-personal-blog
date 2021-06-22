package com.zhiqian.mapper;

import com.zhiqian.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Select("select count(*) from user")
    int getUserCount();

    int update(@Param("user") User user);


    User getUser();
}
