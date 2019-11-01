package com.chinasoft.service.impl;

import com.chinasoft.util.MyMapper;
import com.chinasoft.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: 汪毅
 * @Date: 2018/5/2 17:13
 * @Description:
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private MyMapper<T> mapper;

    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    public List<T> select(T t) {
        return mapper.select(t);
    }

    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    public T selectByPrimaryKey(Object o) {
        return mapper.selectByPrimaryKey(o);
    }

    public int insert(T t) {
        return mapper.insert(t);
    }

    public int insertSelective(T t) {
        return mapper.insertSelective(t);
    }

    public int delete(T t) {
        return mapper.delete(t);
    }

    public int deleteByPrimaryKey(Object o) {
        return mapper.deleteByPrimaryKey(o);
    }

    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    public int updateByPrimaryKeySelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    public int selectCountByExample(Object o) {
        return mapper.selectCountByExample(o);
    }

    public int deleteByExample(Object o) {
        return mapper.deleteByExample(o);
    }

    public List<T> selectByExample(Object o) {
        return mapper.selectByExample(o);
    }

    public int updateByExampleSelective(T t, Object o) {
        return mapper.updateByExampleSelective(t, o);
    }

    public int updateByExample(T t, Object o) {
        return mapper.updateByExample(t, o);
    }

}
