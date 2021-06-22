package com.zhiqian.service;

import com.zhiqian.mapper.BlogMapper;
import com.zhiqian.mapper.TypeMapper;
import com.zhiqian.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        blogMapper.deleteBlogByTypeId(id);
        return typeMapper.deleteTypeById(id);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public int getTypeCount() {
        return typeMapper.getTypeCount();
    }
}
