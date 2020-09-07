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

    /**
     * @Author Derry Lin
     * @Description 查询菜品
     * @Date 下午3:22 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Dish>
     **/
    List<Dish> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:22 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Dish
     **/
    Dish selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据菜名查询
     * @Date 下午3:22 2020/9/7
     * @Param [name]
     * @return edu.hust.dao.dto.Dish
     **/
    Dish selectByName(@Param("name") String name);

    /**
     * @Author Derry Lin
     * @Description 添加菜品
     * @Date 下午3:22 2020/9/7
     * @Param [dish]
     * @return int
     **/
    int add(@Param("dish") Dish dish);

    /**
     * @Author Derry Lin
     * @Description 批量添加菜品
     * @Date 下午3:22 2020/9/7
     * @Param [dishList]
     * @return int
     **/
    int addBatch(@Param("dish_list") List<Dish> dishList);

    /**
     * @Author Derry Lin
     * @Description 更新菜品
     * @Date 下午3:22 2020/9/7
     * @Param [dish]
     * @return int
     **/
    int update(@Param("dish") Dish dish);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:22 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据菜名删除
     * @Date 下午3:23 2020/9/7
     * @Param [name]
     * @return int
     **/
    int deleteByName(@Param("name") String name);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:36 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
