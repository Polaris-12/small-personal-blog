package com.zhiqian.service;

import com.zhiqian.pojo.Type;

import java.util.List;


public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> listType();

    Type getTypeByName(String name);

    int getTypeCount();
}
