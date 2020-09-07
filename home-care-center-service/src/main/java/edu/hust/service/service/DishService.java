package edu.hust.service.service;

import edu.hust.dao.dto.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Dish类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 10:56
 **/
public interface DishService {

    /**
     * 查询菜品
     * @return
     */
    List<Dish> getDishList();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Dish getDishById(String id);

    /**
     * 根据菜名查询
     * @param name
     * @return
     */
    Dish getDishByName(String name);

    /**
     * 添加菜品
     * @param dish
     */
    void addDish(Dish dish);

    /**
     * 批量添加菜品
     * @param dishList
     */
    void addDishList(List<Dish> dishList);

    /**
     * 更新菜品
     * @param dish
     */
    void updateDish(Dish dish);

    /**
     * 根据id删除
     * @param id
     */
    void deleteDishById(String id);

    /**
     * 根据菜名删除
     * @param name
     */
    void deleteDishByName(String name);

    /**
     * 删除所有
     */
    void deleteAllDish();

}
