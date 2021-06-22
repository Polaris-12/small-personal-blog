package com.zhiqian.mapper;

import com.zhiqian.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    @Insert("insert into type(name) values(#{type.name})")
    int saveType(@Param("type") Type type);

    @Select("select * from type where id=#{id}")
    Type getTypeById(@Param("id") Long id);

    @Delete("delete from type where id=#{id}")
    int deleteTypeById(@Param("id") Long id);

    @Update("update type set name=#{type.name} where id=#{type.id}")
    int updateType(@Param("type") Type type);

    List<Type> listType();

    @Select("select * from type where name=#{name}")
    Type getTypeByName(@Param("name") String name);

    @Select("select count(*) from type")
    int getTypeCount();
}
