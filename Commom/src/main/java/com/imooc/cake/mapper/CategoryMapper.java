package com.imooc.cake.mapper;

import com.imooc.cake.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 分类数据接口
 * @author passionlife
 */
public interface CategoryMapper {
    /**
     * 得到分类集合
     * @return
     */
    @Select("select id,name,create_time createTime,update_time updateTime from category")
    List<Category> getCategories();

    /**
     * 删除一个分类
     * @param id
     */
    @Delete("delete from category where id=#{id}")
    void deleteById(@Param("id")Long id);

    /**
     * 增加一个分类
     * @param category
     */
    @Insert("insert category (name,create_time,update_time) values (#{category.name},#{category.createTime},#{category.updateTime})")
    void insert(@Param("category") Category category);

}
