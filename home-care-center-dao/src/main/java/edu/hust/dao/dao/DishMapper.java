package edu.hust.dao.dao;

import edu.hust.dao.dto.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Dish类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 17:17
 **/
public interface DishMapper {

    //查询菜品
    List<Dish> selectList();

    //根据id查询
    Dish selectById(@Param("id") String id);

    //根据菜名查询
    Dish selectByName(@Param("name") String name);

    //添加菜品
    int add(@Param("dish") Dish dish);

    //批量添加菜品
    int addBatch(@Param("dish_list") List<Dish> dishList);

    //更新菜品
    int update(@Param("dish") Dish dish);

    //根据id删除
    int deleteById(@Param("id") String id);

    //根据菜名删除
    int deleteByName(@Param("name") String name);

    //删除所有
    int deleteAll();

}
