package com.ny.service;

import com.ny.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章分类业务层接口
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAllTypeAndBlog();

    List<Type> getBlogType();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);
}
