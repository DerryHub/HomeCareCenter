package edu.hust.service.service;

import edu.hust.dao.dto.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Dish类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 10:56
 **/
@Service
public interface DishService {

    /**
     * @Author Derry Lin
     * @Description 查询菜品
     * @Date 下午3:05 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Dish>
     **/
    List<Dish> getDishList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:05 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Dish
     **/
    Dish getDishById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据菜名查询
     * @Date 下午3:05 2020/9/7
     * @Param [name]
     * @return edu.hust.dao.dto.Dish
     **/
    Dish getDishByName(String name);

    /**
     * @Author Derry Lin
     * @Description 添加菜品
     * @Date 下午3:05 2020/9/7
     * @Param [dish]
     * @return void
     **/
    void addDish(Dish dish);

    /**
     * @Author Derry Lin
     * @Description 批量添加菜品
     * @Date 下午3:05 2020/9/7
     * @Param [dishList]
     * @return void
     **/
    void addDishList(List<Dish> dishList);

    /**
     * @Author Derry Lin
     * @Description 更新菜品
     * @Date 下午3:05 2020/9/7
     * @Param [dish]
     * @return void
     **/
    void updateDish(Dish dish);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:06 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteDishById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据菜名删除
     * @Date 下午3:06 2020/9/7
     * @Param [name]
     * @return void
     **/
    void deleteDishByName(String name);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:19 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllDish();

}
