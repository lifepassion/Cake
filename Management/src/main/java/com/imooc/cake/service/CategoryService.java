package com.imooc.cake.service;

import com.imooc.cake.common.MybatisUtils;
import com.imooc.cake.entity.Category;
import com.imooc.cake.mapper.CategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author passionlife
 */
public class CategoryService {

    /**
     * 得到分类集合
     * @return
     */
    public List<Category> getCategories(){
        SqlSession sqlSession= MybatisUtils.openSession();
        try {
            CategoryMapper categoryMapper=sqlSession.getMapper(CategoryMapper.class);
            return  categoryMapper.getCategories();
        } finally {
            sqlSession.close();
        }
    }
}
