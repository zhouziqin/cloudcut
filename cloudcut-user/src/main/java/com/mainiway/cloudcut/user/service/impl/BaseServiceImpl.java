package com.mainiway.cloudcut.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mainiway.cloudcut.user.dao.BaseDao;
import com.mainiway.cloudcut.user.service.BaseService;


public abstract class BaseServiceImpl<T, PK> implements BaseService<T, PK> {

    @Autowired
    private BaseDao<T, PK> dao;

    @Override
    public T selectByPrimaryKey(PK key) {
        return dao.selectByPrimaryKey(key);
    }

    @Override
    public List<T> selectByExample(T entity) {
        return dao.selectByExample(entity);
    }

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public int insert(T entity) {
        return dao.insert(entity);
    }

    @Override
    public int insertSelective(T entity) {
        return dao.insertSelective(entity);
    }

    @Override
    public int update(T entity) {
        return dao.update(entity);
    }

    @Override
    public int updateSelective(T entity) {
        return dao.updateSelective(entity);
    }

    @Override
    public int deleteByPrimaryKey(PK key) {
        return dao.deleteByPrimaryKey(key);
    }

}
