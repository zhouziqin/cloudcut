package com.mainiway.cloudcut.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 通用接口
 */
@Service
public interface BaseService<T, PK> {

    T selectByPrimaryKey(PK key);
    
    List<T> selectByExample(T entity);
    
    List<T> selectAll();
    
    int insert(T entity);
    
    int insertSelective(T entity);

    int update(T entity);

    int updateSelective(T entity);
    
    int deleteByPrimaryKey(PK key);
    
    int save(T entity);
    
}
