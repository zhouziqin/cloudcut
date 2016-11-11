package com.mainiway.cloudcut.user.dao;

import java.util.List;

public interface BaseDao<T, PK> {
    
    T selectByPrimaryKey(PK key);
    
    List<T> selectByExample(T entity);
    
    List<T> selectAll();
    
    int insert(T entity);
    
    int insertSelective(T entity);

    int update(T entity);

    int updateSelective(T entity);
    
    int deleteByPrimaryKey(PK key);
    
}
