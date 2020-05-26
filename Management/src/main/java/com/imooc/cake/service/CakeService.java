package com.imooc.cake.service;

import com.imooc.cake.common.MybatisUtils;
import com.imooc.cake.entity.Cake;
import com.imooc.cake.mapper.CakeMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author passionlife
 */
public class CakeService {

    /**
     * 根据分类查询蛋糕信息
     * @param categoryId 分类id
     * @param page 第几页
     * @param size 每页显示多少条
     * @return 分类蛋糕集合
     */
     public List<Cake> getCakesByCategoryId(Long categoryId, Integer page, Integer size){
        SqlSession sqlSession= MybatisUtils.openSession();
        try {
            CakeMapper mapper=sqlSession.getMapper(CakeMapper.class);
            return mapper.getCakesByCategoryId(categoryId,(page-1)*size,size);
        } finally {
            sqlSession.close();
        }

     }

    /**
     * 分页查询蛋糕信息
     * @param page 从那条开始查询
     * @param size 每页显示多少条
     * @return 蛋糕集合
     */
    public List<Cake> getCakes(Integer page, Integer size){
        SqlSession sqlSession= MybatisUtils.openSession();
        try {
            CakeMapper mapper=sqlSession.getMapper(CakeMapper.class);
            return mapper.getCakes((page-1)*size,size);
        } finally {
            sqlSession.close();
        }
    }


    /**
     * 增加蛋糕
     * @param cake
     */
    public void addCake (Cake cake){
        SqlSession sqlSession= MybatisUtils.openSession();
        try {
            CakeMapper mapper=sqlSession.getMapper(CakeMapper.class);
            mapper.addCake(cake);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


}
