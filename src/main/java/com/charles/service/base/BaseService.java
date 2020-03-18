package com.charles.service.base;


import com.charles.util.Page;

import java.util.List;

/**
 * @author xiaos
 * @version $Id: BaseService.java, v0.1 2018/10/30 10:09 xiaos Exp $$
 */
public interface BaseService<T> {
    T selectOne(T entity);

    T selectById(Object id);

    List<T> selectList(T entity);

    List<T> selectListAll();

    int selectCount(T entity);

    int insert(T entity);

    int insertSelective(T entity);

    int delete(T entity);

    int deleteById(Object id);

    int updateById(T entity);

    List<T> selectByExample(Object example);

    int updateSelectiveById(T entity);

    int selectCountByExample(Object example);

    Page<T> selectPage(int pageNum, int pageSize, T condition);

    Page<T> selectPageByExample(int pageNum, int pageSize, Object exampleCondition);

}
