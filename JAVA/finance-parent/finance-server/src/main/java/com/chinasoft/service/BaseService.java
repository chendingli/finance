package com.chinasoft.service;

import java.util.List;

/**
 * @Auther: 汪毅
 * @Date: 2018/5/3 08:48
 * @Description:
 */
public interface BaseService<T> {

    T selectOne(T t);

    List<T> select(T t);

    int selectCount(T t);

    T selectByPrimaryKey(Object o);

    int insert(T t);

    int insertSelective(T t);

    int delete(T t);

    int deleteByPrimaryKey(Object o);

    int updateByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);

    int selectCountByExample(Object o);

    int deleteByExample(Object o);

    List<T> selectByExample(Object o);

    int updateByExampleSelective(T t, Object o);

    int updateByExample(T t, Object o);

}
