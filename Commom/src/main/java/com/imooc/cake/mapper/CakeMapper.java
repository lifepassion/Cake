package com.imooc.cake.mapper;

import com.imooc.cake.entity.Cake;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 蛋糕分类数据接口
 * @author passionlife
 */
public interface CakeMapper {

    /**
     * 分页查询蛋糕信息
     * @param skip 从那条开始查询
     * @param size 每页显示多少条
     * @return 蛋糕集合
     */
    @Select("select id,category_id categoryId,name,level,price,small_img smallImg,create_Time createTime,update_time updateTime "
            +"from cake order by create_time desc limit #{skip},#{size}")
    List<Cake> getCakes(@Param("skip")Integer skip, @Param("size")Integer size);

    /**
     * 根据分类查询蛋糕信息
     * @param categoryId 分类id
     * @param skip 从那条开始查询
     * @param size 每页显示多少条
     * @return 分类蛋糕集合
     */
    @Select("select id,category_id categoryId,name,level,price,small_img smallImg,create_Time createTime,update_time updateTime "
            +"from cake where category_id=#{categoryId} order by create_time desc limit #{skip},#{size}")
    List<Cake> getCakesByCategoryId(@Param("categoryId") Long categoryId, @Param("skip")Integer skip, @Param("size")Integer size);

    /**
     * 根据分类查询蛋糕总数
     * @param categoryId 分类id
     * @return 分类蛋糕数量
     */
    @Select("select count(*) from cake where category_id = #{categoryId}")
    int countCakesByCategoryId(@Param("categoryId") Long categoryId);


    /**
     * 增加蛋糕
     * @param cake
     */
    @Insert("insert into cake (category_id ,name,level,price,small_img ,create_Time ,update_time)"+
            "values (#{cake.categoryId},#{cake.name},#{cake.level},#{cake.price},#{cake.smallImg},#{cake.createTime},#{cake.updateTime}) "
    )
    void addCake(@Param("cake") Cake cake);

    /**
     * 获得蛋糕图片
     * @param id
     * @return
     */
    @Select("select small_image smallImage from cake where id=@{id} for update")
    Cake getImage(@Param("id") Long id);
}
